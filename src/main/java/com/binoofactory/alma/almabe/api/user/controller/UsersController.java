package com.binoofactory.alma.almabe.api.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.common.service.DirectSendAPIService;
import com.binoofactory.alma.almabe.api.user.data.UserStatus;
import com.binoofactory.alma.almabe.api.user.data.UserType;
import com.binoofactory.alma.almabe.api.user.model.entity.Users;
import com.binoofactory.alma.almabe.api.user.service.UserJwtService;
import com.binoofactory.alma.almabe.api.user.service.UserService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.PasswordUtil;
import com.binoofactory.alma.almabe.common.utils.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = {"회원"}, description = "User", basePath = "")
@Slf4j
public class UsersController extends BfAbsUserController {

    private final DirectSendAPIService directSendAPIService;

    private final PasswordUtil passwordUtil;

    @Autowired
    public UsersController(UserService userService, UserJwtService userJwtService,
            DirectSendAPIService directSendAPIService, PasswordUtil passwordUtil) {
        super(userService, userJwtService);

        this.directSendAPIService = directSendAPIService;

        this.passwordUtil = passwordUtil;
    }

    @GetMapping(value = "/user/check/{userId}")
    @ApiOperation(value = "회원 중복 검사", notes = "회원 중복 검사")
    public ResponseEntity checkUserId(@PathVariable("userId") @ApiParam("아이디") String userId) throws Exception {
        return super.checkUserId(userId);
    }

    @PostMapping(value = "/user/add")
    @ApiOperation(value = "회원 가입(일반)", notes = "회원 가입(일반)")
    public ResponseEntity add(@RequestBody Users user) throws Exception {
        return ResponseUtil.sendResponse(userService.add(user, null));
    }

    @PostMapping(value = "/user/login")
    @ApiOperation(value = "회원 로그인", notes = "회원 로그인")
    public ResponseEntity login(
            @RequestParam(value = "userId", defaultValue = "user1") @ApiParam("아이디") String userId,
            @RequestParam(value = "password", defaultValue = "user1_") @ApiParam("비밀번호") String password,
            HttpServletRequest request) throws Exception {
        return super.login(userId, password, request);
    }

    @GetMapping(value = "/user/findMe")
    @ApiOperation(value = "로그인 된 회원 정보 조회", notes = "로그인 된 회원 정보 조회")
    public ResponseEntity findMe(HttpServletRequest request) throws Exception {
        return super.findMe(request);
    }

    @GetMapping(value = "/user/check/tokenExpired")
    @ApiOperation(value = "회원 토큰 만료 여부 검사", notes = "회원 토큰 만료 여부 검사")
    public ResponseEntity checkTokenExpired(HttpServletRequest request) {
        return ResponseUtil.sendResponse(userJwtService.checkTokenExpired(request));
    }

    @PostMapping(value = "/user/refreshToken/{refreshToken}")
    @ApiOperation(value = "회원 토큰 재발급", notes = "회원 토큰 재발급")
    public ResponseEntity regenerateToken(
            @PathVariable("refreshToken") @ApiParam(value = "재발급될 토큰", required = true) String refreshToken) {
        return ResponseUtil.sendResponse(userJwtService.generateTokenByRefreshToken(refreshToken));
    }

    @PatchMapping(value = "/user/editPassword")
    @ApiOperation(value = "회원 비밀번호 수정", notes = "회원 비밀번호 수정")
    public ResponseEntity editPassword(@RequestBody Users user, HttpServletRequest request) throws Exception {
        userService.editPassword(user, request);
        return ResponseUtil.sendResponse(true);
    }

    @PatchMapping(value = "/user/edit")
    @ApiOperation(value = "회원 정보 수정(비밀번호 제외)", notes = "회원 정보 수정(비밀번호 제외)")
    public ResponseEntity edit(@RequestBody Users user, HttpServletRequest request) throws Exception {
        userService.edit(user, request);
        return ResponseUtil.sendResponse(true);
    }

    @DeleteMapping(value = "/user/remove")
    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴")
    public ResponseEntity remove(HttpServletRequest request) throws Exception {
        Users user = userJwtService.getUserInfoByToken(request);

        userService.remove(user, request);
        return ResponseUtil.sendResponse(null);
    }

    @GetMapping(value = "/user/findId")
    @ApiOperation(value = "아이디 찾기", notes = "아이디 찾기")
    public ResponseEntity<Users> findId(
            @RequestParam(value = "name", defaultValue = "김유저") String name,
            @RequestParam(value = "email", defaultValue = "user1@codeidea.dev") String email,
            HttpServletRequest request) throws Exception {
        BfListResponse<Users> users = userService.findAll(Users.builder()
            .deleted(false)
            .name(name)
            .email(email)
            .build(), new BfPage(1, 5), request);
        if(users.getList().size() == 0) {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "회원 정보가 존재하지 않습니다.");
        }

        return ResponseUtil.sendResponse(users.getList().get(0));
    }

    @PostMapping(value = "/user/resetPassword")
    @ApiOperation(value = "비밀번호 재설정", notes = "비밀번호 재설정")
    public ResponseEntity<Users> resetPassword(
            @RequestParam(value = "userId", defaultValue = "user1") String userId,
            @RequestParam(value = "email", defaultValue = "user1@codeidea.dev") String email,
            HttpServletRequest request) throws Exception {
        BfListResponse<Users> users = userService.findAll(Users.builder()
                .deleted(false)
                .userId(userId)
                .email(email)
                .build(), new BfPage(1, 5), request);
        if(users.getList().size() == 0) {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "회원 정보가 존재하지 않습니다.");
        }
        Users user = users.getList().get(0);

        String password = Math.round(Math.random() * 1000000) + "";
        user.setPassword(passwordUtil.encode(password));

        userService.edit(user, request);

        /*
        Map<String, String> bodyMap = new HashMap<>();

        bodyMap.put("title", "임시 비밀번호입니다.");
        bodyMap.put("message", "임시 비밀번호는 [" + password + "]입니다.");
        bodyMap.put("name", user.getName());
        bodyMap.put("phoneNo", user.getMobilePhoneNo());

        directSendAPIService.sendSMS(user.getPhoneNo(), bodyMap);
        */
        return ResponseUtil.sendResponse(password);
    }

}