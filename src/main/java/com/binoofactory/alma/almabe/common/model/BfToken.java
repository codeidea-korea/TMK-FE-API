package com.binoofactory.alma.almabe.common.model;

import com.binoofactory.alma.almabe.api.user.model.entity.Users;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BfToken extends Users {
    private String accessToken;
    private String refreshToken;
    private String userCode;
    private String expiredAt;

    public BfToken(String accessToken, String refreshToken, String userCode) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userCode = userCode;
    }

    public BfToken(Users users) {
        super(users);
    }
}
