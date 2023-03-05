package com.binoofactory.alma.almabe.api.user.data;

import lombok.Getter;

@Getter
public enum UserStatus {
    NONE("미승인", 1),
    WAIT("승인대기", 2),
    NORMAL("승인완료", 3); // 일반회원의 경우 바로 승인완료, 여행사 회원의 경우 승인대기 -> 승인완료

    private final String description;

    private final int code;

    UserStatus(String description, int code) {
        this.description = description;
        this.code = code;
    }
}
