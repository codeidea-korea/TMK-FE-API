package com.binoofactory.alma.almabe.api.user.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.user.data.UserType;
import com.binoofactory.alma.almabe.api.user.model.entity.Users;
import com.binoofactory.alma.almabe.api.user.repos.jpa.UserRepos;
import com.binoofactory.alma.almabe.api.user.service.UserJwtService;
import com.binoofactory.alma.almabe.common.model.BfToken;
import com.binoofactory.alma.almabe.common.utils.BfJwtUtil;
import com.binoofactory.alma.almabe.common.utils.DateUtil;
import com.querydsl.core.util.StringUtils;

import io.jsonwebtoken.ExpiredJwtException;

@Service
public class UserJwtServiceImpl implements UserJwtService {

    private final UserRepos userRepos;

    private final BfJwtUtil jwtUtil;
    private final DateUtil dateUtil;

    @Autowired
    public UserJwtServiceImpl(UserRepos userRepos, BfJwtUtil jwtUtil, DateUtil dateUtil) {
        this.userRepos = userRepos;

        this.jwtUtil = jwtUtil;
        this.dateUtil = dateUtil;
    }

    private String removeBearer(String token) {
        if(StringUtils.isNullOrEmpty(token) || (!token.toLowerCase().contains("bearer "))) {
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다.");
        }

        return token.replace("bearer ", "").replace("Bearer ", "");
    }

    private void checkToken(String token) {
        if(StringUtils.isNullOrEmpty(token)) {
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다.");
        }

        if(jwtUtil.isExpired(token)) {
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다. 다시 로그인을 해주세요.");
        }

        String userId = jwtUtil.getUserId(token);
        if(StringUtils.isNullOrEmpty(userId)) {
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED, "회원 정보가 존재하지 않습니다.");
        }

        Users user = userRepos.findByUserId(userId);
        if(Objects.isNull(user)) {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "회원 정보가 존재하지 않습니다.");
        }
    }

    @Override
    public BfToken generateToken(Users user, String userCode) {
        BfToken token = new BfToken(user);

        token.setAccessToken(jwtUtil.generateAccessToken(user));
        token.setRefreshToken(jwtUtil.generateRefreshToken(user));

        LocalDateTime localDateTime = dateUtil.getThisTime();
        localDateTime = localDateTime.plusSeconds(BfJwtUtil.ACCESS_TOKEN_ALIVE_TIME / 1000);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        token.setExpiredAt(localDateTime.format(formatter));

        return token;
    }

    @Override
    public Users getUserInfoByToken(HttpServletRequest request) {
        String token = removeBearer(request.getHeader("Authorization"));

        checkToken(token);
        String userId = jwtUtil.getUserId(token);

        Users user = userRepos.findByUserId(userId);
        return user;
    }

    @Override
    public boolean checkTokenExpired(HttpServletRequest request) {
        String token = removeBearer(request.getHeader("Authorization"));

        try {
            return jwtUtil.isExpired(token);
        } catch (ExpiredJwtException e) {
            e.getStackTrace();
            return true;
        }
    }

    @Override
    public BfToken generateTokenByRefreshToken(String refreshToken) {
        if(jwtUtil.isExpired(refreshToken)) {
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다. 다시 로그인을 해주세요.");
        }
        
        String userId = jwtUtil.getUserIdByRefresh(refreshToken);

        Users user = userRepos.findByUserId(userId);
        if(user == null) {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "회원 정보가 존재하지 않습니다.");
        }
        
        BfToken token = new BfToken(user);

        token.setAccessToken(jwtUtil.generateAccessToken(user));
        token.setRefreshToken(jwtUtil.generateRefreshToken(user));

        return token;
    }

}