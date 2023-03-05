package com.binoofactory.alma.almabe.api.coupon.model.docs;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Document
public class CouponUsedHistory {
    @Id
    @ApiModelProperty(value = "몽고 식별자")
    private String docId;

    @ApiModelProperty("쿠폰 식별자")
    private long couponId;

    @ApiModelProperty("사용자 식별자")
    private long userId;

    @ApiModelProperty("사용자 이름")
    private String userName;

    @ApiModelProperty("사용자 전화번호")
    private String userPhone;

    @ApiModelProperty("사용자 이메일")
    private String userEmail;

    @ApiModelProperty("사용일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime usedAt;

    @ApiModelProperty("예약 식별자")
    private long reservateId;

    @Builder
    public CouponUsedHistory(String docId, long couponId, long userId, String userName, String userPhone,
        String userEmail, LocalDateTime usedAt, long reservateId) {
        super();
        this.docId = docId;
        this.couponId = couponId;
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.usedAt = usedAt;
        this.reservateId = reservateId;
    }
}
