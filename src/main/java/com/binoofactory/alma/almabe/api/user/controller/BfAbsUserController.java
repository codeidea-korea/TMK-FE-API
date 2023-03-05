package com.binoofactory.alma.almabe.api.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.user.data.UserType;
import com.binoofactory.alma.almabe.api.user.model.entity.Users;
import com.binoofactory.alma.almabe.api.user.service.UserJwtService;
import com.binoofactory.alma.almabe.api.user.service.UserService;
import com.binoofactory.alma.almabe.common.utils.ResponseUtil;

public abstract class BfAbsUserController {

    protected final UserService userService;
    protected final UserJwtService userJwtService;

    public BfAbsUserController(UserService userService, UserJwtService userJwtService) {
        this.userService = userService;
        this.userJwtService = userJwtService;
    }

    public ResponseEntity checkDuplicated(String userId) throws Exception {
        return ResponseUtil.sendResponse(userService.checkUserId(userId));
    }

    public ResponseEntity login(String userId, String password, HttpServletRequest request) throws Exception {
        Users user = new Users();

        user.setUserId(userId);
        user.setPassword(password);

        return ResponseUtil.sendResponse(userService.login(user, request));
    }

    public ResponseEntity findMe(HttpServletRequest request) throws Exception {
        return ResponseUtil.sendResponse(userService.findMe(request));
    }

}