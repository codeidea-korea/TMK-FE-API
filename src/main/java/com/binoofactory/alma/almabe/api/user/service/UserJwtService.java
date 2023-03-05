package com.binoofactory.alma.almabe.api.user.service;

import javax.servlet.http.HttpServletRequest;

import com.binoofactory.alma.almabe.api.user.data.UserType;
import com.binoofactory.alma.almabe.api.user.model.entity.Users;
import com.binoofactory.alma.almabe.common.model.BfToken;

public interface UserJwtService {

    BfToken generateToken(Users user, String userCode);

    Users getUserInfoByToken(HttpServletRequest request);
    
    boolean checkTokenExpired(HttpServletRequest request);

    BfToken generateTokenByRefreshToken(String refreshToken);

}