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
@Table(name = "service_detail")
@Getter
@Setter
@NoArgsConstructor
public class ServiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "brand_id")
    @ApiModelProperty("브랜드 일련번호")
    private long brandId;

    @Column(name = "name")
    @ApiModelProperty("국문명")
    private String name;

    @Column(name = "name_eng")
    @ApiModelProperty("영문명")
    private String nameEng;

    @Column(name = "description")
    @ApiModelProperty("설명")
    private String description;

    @Column(name = "visibled_help")
    @ApiModelProperty("도움말 노출여부")
    private boolean visibledHelp;

    @Column(name = "etc")
    @ApiModelProperty("기타")
    private String etc;

    @Builder
    public ServiceDetail(long id, long brandId, String name, String nameEng, String description, boolean visibledHelp,
        String etc) {
        super();
        this.id = id;
        this.brandId = brandId;
        this.name = name;
        this.nameEng = nameEng;
        this.description = description;
        this.visibledHelp = visibledHelp;
        this.etc = etc;
    }
}
