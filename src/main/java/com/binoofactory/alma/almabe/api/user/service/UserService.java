package com.binoofactory.alma.almabe.api.user.service;

import javax.servlet.http.HttpServletRequest;

import com.binoofactory.alma.almabe.api.user.model.entity.Users;
import com.binoofactory.alma.almabe.common.model.BfToken;
import com.binoofactory.alma.almabe.common.model.service.BfCRUDService;

public interface UserService extends BfCRUDService<Users> {

    boolean checkUserId(String userId);

    BfToken login(Users user, HttpServletRequest request) throws Exception;

    Users findMe(HttpServletRequest request) throws Exception;
    
    Users editPassword(Users user, HttpServletRequest httpServletRequest) throws Exception;

    Users edit(Users user, HttpServletRequest request) throws Exception;

    void remove(Users user, HttpServletRequest request) throws Exception;

}