package com.binoofactory.alma.almabe.api.feecontract.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.binoofactory.alma.almabe.api.feecontract.data.DiscountType;
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
@Table(name = "service_discount")
@Getter
@Setter
@NoArgsConstructor
public class ServiceDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "service_id")
    @ApiModelProperty("서비스 일련번호")
    private long serviceId;

    @Column(name = "service_type_code")
    @ApiModelProperty("서비스 구분 코드")
    private String serviceTypeCode;

    @Column(name = "sells_type_code")
    @ApiModelProperty("판매 구분 코드")
    private String sellsTypeCode;

    @Column(name = "visibled")
    @ApiModelProperty("노출여부")
    private boolean visibled;

    @Column(name = "event_name")
    @ApiModelProperty("이벤트명")
    private String eventName;

    @Column(name = "discount_type")
    @ApiModelProperty("할인 구분")
    private DiscountType discountType;

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

    @Column(name = "rent_efective_started_at")
    @ApiModelProperty("렌탈 시작 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime rentEfectiveStartedAt;

    @Column(name = "rent_efective_ended_at")
    @ApiModelProperty("렌탈 종료 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime rentEfectiveEndedAt;

    @Builder
    public ServiceDiscount(long id, long serviceId, String serviceTypeCode, String sellsTypeCode, boolean visibled,
        String eventName, DiscountType discountType, LocalDateTime reservateEfectiveStartedAt,
        LocalDateTime reservateEfectiveEndedAt, LocalDateTime rentEfectiveStartedAt,
        LocalDateTime rentEfectiveEndedAt) {
        super();
        this.id = id;
        this.serviceId = serviceId;
        this.serviceTypeCode = serviceTypeCode;
        this.sellsTypeCode = sellsTypeCode;
        this.visibled = visibled;
        this.eventName = eventName;
        this.discountType = discountType;
        this.reservateEfectiveStartedAt = reservateEfectiveStartedAt;
        this.reservateEfectiveEndedAt = reservateEfectiveEndedAt;
        this.rentEfectiveStartedAt = rentEfectiveStartedAt;
        this.rentEfectiveEndedAt = rentEfectiveEndedAt;
    }
}
