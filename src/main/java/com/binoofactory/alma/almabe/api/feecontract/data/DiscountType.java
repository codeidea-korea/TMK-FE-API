package com.binoofactory.alma.almabe.api.feecontract.data;

import lombok.Getter;

@Getter
public enum DiscountType {
    NONE("할인없음", 1);

    private final String description;

    private final int code;

    DiscountType(String description, int code) {
        this.description = description;
        this.code = code;
    }
}
