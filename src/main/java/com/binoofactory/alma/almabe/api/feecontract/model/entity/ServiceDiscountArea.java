package com.binoofactory.alma.almabe.api.feecontract.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "service_discount_area")
@Getter
@Setter
@NoArgsConstructor
public class ServiceDiscountArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "service_discount_id")
    @ApiModelProperty("서비스 할인 일련번호")
    private long serviceDiscountId;

    @Column(name = "national_authems_code")
    @ApiModelProperty("국가 코드")
    private String nationalAuthemsCode;

    @Column(name = "stats_code")
    @ApiModelProperty("주 코드")
    private String statsCode;

    @Column(name = "citys_code")
    @ApiModelProperty("도시 코드")
    private String citysCode;

    @Column(name = "branch_code")
    @ApiModelProperty("지점 코드")
    private String branchCode;

    @Builder
    public ServiceDiscountArea(long id, long serviceDiscountId, String nationalAuthemsCode, String statsCode,
        String citysCode, String branchCode) {
        super();
        this.id = id;
        this.serviceDiscountId = serviceDiscountId;
        this.nationalAuthemsCode = nationalAuthemsCode;
        this.statsCode = statsCode;
        this.citysCode = citysCode;
        this.branchCode = branchCode;
    }
}
