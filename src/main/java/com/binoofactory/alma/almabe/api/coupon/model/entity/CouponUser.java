package com.binoofactory.alma.almabe.api.coupon.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coupon_users")
@Getter
@Setter
@NoArgsConstructor
public class CouponUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "coupon_id")
    @ApiModelProperty("쿠폰 일련번호")
    private long couponId;
    
    @Column(name = "user_id")
    @ApiModelProperty("사용자 일련번호")
    private long userId;

    @Column(name = "user_name")
    @ApiModelProperty("사용자명")
    private String userName;

    @Column(name = "user_phone")
    @ApiModelProperty("사용자 전화번호")
    private String userPhoneNo;

    @Column(name = "user_email")
    @ApiModelProperty("사용자 이메일")
    private String userEmail;
    
    @Column(name = "used")
    @ApiModelProperty("사용여부")
    private boolean used;
    
    @Column(name = "reservation_id")
    @ApiModelProperty("예약 일련번호")
    private long reservationId;

    @Column(name = "reservated_at")
    @ApiModelProperty("예약 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime reservatedAt;

    @Column(name = "pickup_at")
    @ApiModelProperty("픽업 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime pickupAt;

    @Builder
    public CouponUser(long id, long couponId, long userId, String userName, String userPhoneNo, String userEmail,
        boolean used, long reservationId, LocalDateTime reservatedAt, LocalDateTime pickupAt) {
        super();
        this.id = id;
        this.couponId = couponId;
        this.userId = userId;
        this.userName = userName;
        this.userPhoneNo = userPhoneNo;
        this.userEmail = userEmail;
        this.used = used;
        this.reservationId = reservationId;
        this.reservatedAt = reservatedAt;
        this.pickupAt = pickupAt;
    }
}
