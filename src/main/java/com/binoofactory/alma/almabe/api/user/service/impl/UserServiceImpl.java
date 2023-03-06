package com.binoofactory.alma.almabe.api.user.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.user.model.docs.LoginHistory;
import com.binoofactory.alma.almabe.api.user.model.entity.Users;
import com.binoofactory.alma.almabe.api.user.repos.jpa.UserRepos;
import com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.UsersDslRepos;
import com.binoofactory.alma.almabe.api.user.repos.mongo.LoginHistoryRepos;
import com.binoofactory.alma.almabe.api.user.service.UserJwtService;
import com.binoofactory.alma.almabe.api.user.service.UserService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.model.BfToken;
import com.binoofactory.alma.almabe.common.utils.DateUtil;
import com.binoofactory.alma.almabe.common.utils.PasswordUtil;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepos userRepos;
    private final UsersDslRepos userDslRepos;
    private final LoginHistoryRepos loginHistoryRepos;

    private final UserJwtService userJwtService;

    private final PasswordUtil passwordUtil;
    private final DateUtil dateUtil;

    @Autowired
    public UserServiceImpl(UserRepos userRepos, UsersDslRepos userDslRepos, LoginHistoryRepos loginHistoryRepos,
            UserJwtService userJwtService, PasswordUtil passwordUtil, DateUtil dateUtil) {

        this.userRepos = userRepos;
        this.userDslRepos = userDslRepos;
        this.loginHistoryRepos = loginHistoryRepos;

        this.userJwtService = userJwtService;

        this.passwordUtil = passwordUtil;
        this.dateUtil = dateUtil;
    }

    @Override
    public boolean checkUserId(String userId) {
        Users userInfos = userRepos.findByUserId(userId);
        if(!Objects.isNull(userInfos)) {
            return false;
        }

        return true;
    }

    @Override
    @Transactional
    public Users add(Users user, HttpServletRequest request) throws Exception {
        if(Objects.isNull(user)) {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "회원 정보가 존재하지 않습니다.");
        }
        
        Users userInfos = userRepos.findByUserId(user.getUserId());
        if(!Objects.isNull(userInfos) && !userInfos.getDeleted()) {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "이미 가입된 회원 정보입니다.");
        }

        // TODO: Validation
        user.setDeleted(false);
        LocalDateTime now = dateUtil.getThisTime();
        user.setCreatedAt(now);
        user.setPassword(passwordUtil.encode(user.getPassword()));

        Users userInfo = userRepos.save(user);
        if(Objects.isNull(userInfo)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "오류가 발생하였습니다.");
        }

        return userInfo;
    }

    @Override
    public BfToken login(Users user, HttpServletRequest request) throws Exception {
        String template =
            "Addr:" + request.getRemoteAddr()
            + "/Host:" + request.getRemoteHost()
            + "/User:" + request.getRemoteUser();

        Users userInfos = userRepos.findByUserId(user.getUserId());
        if(Objects.isNull(userInfos) || userInfos.getDeleted()) {
            loginHistoryRepos.save(LoginHistory.builder()
                .isSuccess(false)
                .createDt(LocalDateTime.now())
                .userId(user.getUserId())
                .password(user.getPassword())
                .platformInfo(template)
                .build());

            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "회원 정보가 존재하지 않습니다.");
        }

        if(!passwordUtil.checkEqual(userInfos.getPassword(), user.getPassword())) {
            loginHistoryRepos.save(LoginHistory.builder()
                .isSuccess(false)
                .createDt(LocalDateTime.now())
                .userId(user.getUserId())
                .password(user.getPassword())
                .platformInfo(template)
                .build());

            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

        loginHistoryRepos.save(LoginHistory.builder()
            .isSuccess(true)
            .createDt(LocalDateTime.now())
            .userId(user.getUserId())
            .password(user.getPassword())
            .platformInfo(template)
            .build());

        BfToken token = userJwtService.generateToken(userInfos, template);
        return token;
    }

    @Override
    public Users findMe(HttpServletRequest request) {
        Users userInfos = userJwtService.getUserInfoByToken(request);
        if(Objects.isNull(userInfos)) {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "회원 정보가 존재하지 않습니다.");
        }

        return userInfos;
    }

    @Override
    @Transactional
    public Users editPassword(Users user, HttpServletRequest request) throws Exception {
        Users myInfo = userJwtService.getUserInfoByToken(request);
        if(myInfo == null) {
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED, "먼저 로그인을 해주세요.");
        }

        myInfo.setPassword(passwordUtil.encode(user.getPassword()));
        return userRepos.save(myInfo);
    }

    @Transactional
    @Override
    public Users edit(Users user, HttpServletRequest request) throws Exception {
        Users userInfo = userRepos.findById(user.getId()).orElse(null);
        if(Objects.isNull(userInfo) || userInfo.getDeleted()) {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "회원 정보가 존재하지 않습니다.");
        }

        Users myInfo = userJwtService.getUserInfoByToken(request);
        if(userInfo.getId() != myInfo.getId()) {
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED, "권한이 부족합니다.");
        }

        user.setDeleted(false);
        user.setCreatedAt(myInfo.getCreatedAt());
        user.setUserId(myInfo.getUserId());
        user.setPassword(myInfo.getPassword());
        log.error("user: {}", user);
        return userRepos.save(user);
    }

    @Override
    public void remove(Users user, HttpServletRequest request) {
        Users userInfo = userRepos.findById(user.getId()).orElse(null);
        if(Objects.isNull(userInfo) || userInfo.getDeleted()) {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "회원 정보가 존재하지 않습니다.");
        }

        Users myInfo = userJwtService.getUserInfoByToken(request);
        if(userInfo.getId() != myInfo.getId()) {
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED, "권한이 부족합니다.");
        }

        user.setDeleted(true);
        log.error("user: {}", user);
        userRepos.save(user);
    }

    @Override
    public BfListResponse<Users> findAll(Users user, BfPage bfPage, HttpServletRequest request) throws Exception {
        List<Users> list = userDslRepos.findAll(user, bfPage);
        long count = userDslRepos.countAll(user);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public Users generate(Users user) { return null; }

    @Override
    public void remove(long id, HttpServletRequest request) throws Exception { }

    @Override
    public Users find(long id, HttpServletRequest request) throws Exception { return null; }

}