package com.binoofactory.alma.almabe.api.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "citys")
@Data
@NoArgsConstructor
public class Citys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;

    @Column(name = "national_authems_code")
    @ApiModelProperty("국가 코드")
    private String nationalAuthemsCode;

    @Column(name = "stats_code")
    @ApiModelProperty("주 코드")
    private String statsCode;

    @ApiModelProperty("노출여부")
    @Column(name = "visabled")
    private boolean visabled;

    @Column(name = "name_eng")
    @ApiModelProperty("영문명")
    private String nameEng;

    @Column(name = "name")
    @ApiModelProperty("국문명")
    private String name;

    @Column(name = "code")
    @ApiModelProperty("시 코드")
    private String code;

    @Builder
    public Citys(long id, String nationalAuthemsCode, String statsCode, boolean visabled, String nameEng, String name,
        String code) {
        
        this.id = id;
        this.nationalAuthemsCode = nationalAuthemsCode;
        this.statsCode = statsCode;
        this.visabled = visabled;
        this.nameEng = nameEng;
        this.name = name;
        this.code = code;
    }
}
