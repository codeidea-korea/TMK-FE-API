package com.binoofactory.alma.almabe.api.feecontract.model.entity;

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
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "brand_id")
    @ApiModelProperty("브랜드 일련번호")
    private long brandId;
    
    @Column(name = "service_type_code")
    @ApiModelProperty("서비스 구분 코드")
    private String serviceTypeCode;

    @Column(name = "visabled")
    @ApiModelProperty("노출여부")
    private boolean visabled;

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

    @Column(name = "created_at")
    @ApiModelProperty("생성 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;
    
    @Column(name = "contract_id")
    @ApiModelProperty("contract_id")
    private String contractId;
    
    @Column(name = "product_code")
    @ApiModelProperty("product_code")
    private String productCode;
    
    @Column(name = "iata")
    @ApiModelProperty("iata")
    private String iata;
    
    @Column(name = "corp_diccount_number")
    @ApiModelProperty("corp_diccount_number")
    private String corpDiccountNumber;
    
    @Column(name = "name")
    @ApiModelProperty("서비스명")
    private String name;

    @Builder
    public Services(long id, long brandId, String serviceTypeCode, boolean visabled,
        LocalDateTime reservateEfectiveStartedAt, LocalDateTime reservateEfectiveEndedAt,
        LocalDateTime rentEfectiveStartedAt, LocalDateTime rentEfectiveEndedAt, LocalDateTime createdAt,
        String contractId, String productCode, String iata, String corpDiccountNumber, String name) {
        super();
        this.id = id;
        this.brandId = brandId;
        this.serviceTypeCode = serviceTypeCode;
        this.visabled = visabled;
        this.reservateEfectiveStartedAt = reservateEfectiveStartedAt;
        this.reservateEfectiveEndedAt = reservateEfectiveEndedAt;
        this.rentEfectiveStartedAt = rentEfectiveStartedAt;
        this.rentEfectiveEndedAt = rentEfectiveEndedAt;
        this.createdAt = createdAt;
        this.contractId = contractId;
        this.productCode = productCode;
        this.iata = iata;
        this.corpDiccountNumber = corpDiccountNumber;
        this.name = name;
    }
}
