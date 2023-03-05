package com.binoofactory.alma.almabe.api.feecontract.data;

import lombok.Getter;

@Getter
public enum OptionType {
    NONE("없음", 1);

    private final String description;

    private final int code;

    OptionType(String description, int code) {
        this.description = description;
        this.code = code;
    }
}
