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
@Table(name = "coupons")
@Getter
@Setter
@NoArgsConstructor
public class Coupons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "brand_id")
    @ApiModelProperty("브랜드 일련번호")
    private long brandId;

    @Column(name = "name")
    @ApiModelProperty("쿠폰명")
    private String name;

    @ApiModelProperty("사용 여부")
    @Column(name = "used")
    private boolean used;

    @Column(name = "head_office_coupon_code")
    @ApiModelProperty("본사 쿠폰 코드")
    private String headOfficeCouponCode;
    
    @Column(name = "modified_user_id")
    @ApiModelProperty("답변자 일련번호")
    private long modifiedId;

    @Column(name = "modified_at")
    @ApiModelProperty("수정 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedAt;

    @Column(name = "coupon_type_code")
    @ApiModelProperty("쿠폰 구분 코드")
    private String couponTypeCode;

    @Column(name = "coupon_issued_type_code")
    @ApiModelProperty("쿠폰 발행 구분 코드")
    private String couponIssuedTypeCode;

    @Column(name = "coupon_issued_condition_code")
    @ApiModelProperty("쿠폰 발행 조건 코드")
    private String couponIssuedConditionCode;

    @Column(name = "coupon_issued_to_code")
    @ApiModelProperty("쿠폰 사용 구분 코드")
    private String couponIssuedToCode;
    
    @Column(name = "rent_min_date")
    @ApiModelProperty("렌탈 최소 일")
    private int rentMinDate;
    
    @Column(name = "rent_max_date")
    @ApiModelProperty("렌탈 최대 일")
    private int rentMaxDate;

    @Column(name = "reservate_efective_started_at")
    @ApiModelProperty("예약 시작 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime reservateEfectiveStartedAt;

    @Column(name = "reservate_efective_ended_at")
    @ApiModelProperty("예약 종료 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime reservateEfectiveEndedAt;

    @Column(name = "pickup_efective_started_at")
    @ApiModelProperty("픽업 시작 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime pickupEfectiveStartedAt;

    @Column(name = "pickup_efective_ended_at")
    @ApiModelProperty("픽업 종료 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime pickupEfectiveEndedAt;

    @ApiModelProperty("중복 발행 여부")
    @Column(name = "duplicate_issued")
    private boolean duplicateIssued;

    @ApiModelProperty("리마인드 문자 발송 여부")
    @Column(name = "remind_msg_send_allowed")
    private boolean remindMsgSendAllowed;

    @ApiModelProperty("알림")
    @Column(name = "notice")
    private String notice;

    @Builder
    public Coupons(long id, long brandId, String name, boolean used, String headOfficeCouponCode, long modifiedId,
        LocalDateTime modifiedAt, String couponTypeCode, String couponIssuedTypeCode, String couponIssuedConditionCode,
        String couponIssuedToCode, int rentMinDate, int rentMaxDate, LocalDateTime reservateEfectiveStartedAt,
        LocalDateTime reservateEfectiveEndedAt, LocalDateTime pickupEfectiveStartedAt,
        LocalDateTime pickupEfectiveEndedAt, boolean duplicateIssued, boolean remindMsgSendAllowed, String notice) {
        super();
        this.id = id;
        this.brandId = brandId;
        this.name = name;
        this.used = used;
        this.headOfficeCouponCode = headOfficeCouponCode;
        this.modifiedId = modifiedId;
        this.modifiedAt = modifiedAt;
        this.couponTypeCode = couponTypeCode;
        this.couponIssuedTypeCode = couponIssuedTypeCode;
        this.couponIssuedConditionCode = couponIssuedConditionCode;
        this.couponIssuedToCode = couponIssuedToCode;
        this.rentMinDate = rentMinDate;
        this.rentMaxDate = rentMaxDate;
        this.reservateEfectiveStartedAt = reservateEfectiveStartedAt;
        this.reservateEfectiveEndedAt = reservateEfectiveEndedAt;
        this.pickupEfectiveStartedAt = pickupEfectiveStartedAt;
        this.pickupEfectiveEndedAt = pickupEfectiveEndedAt;
        this.duplicateIssued = duplicateIssued;
        this.remindMsgSendAllowed = remindMsgSendAllowed;
        this.notice = notice;
    }
}
