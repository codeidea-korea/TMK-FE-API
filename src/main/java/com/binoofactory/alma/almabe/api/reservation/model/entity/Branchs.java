package com.binoofactory.alma.almabe.api.reservation.model.entity;

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
@Table(name = "branchs")
@Getter
@Setter
@NoArgsConstructor
public class Branchs {

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

    @Column(name = "citys_code")
    @ApiModelProperty("도시 코드")
    private String citysCode;

    @Column(name = "visabled")
    @ApiModelProperty("이용 가이드 구분 코드")
    private String visabled;

    @Column(name = "name_eng")
    @ApiModelProperty("영문명")
    private String nameEng;

    @Column(name = "name")
    @ApiModelProperty("국문명")
    private String name;

    @Column(name = "pickup_eng")
    @ApiModelProperty("픽업 영문")
    private String pickupEng;

    @Column(name = "pickup")
    @ApiModelProperty("픽업 국문")
    private String pickup;

    @Column(name = "opened_times")
    @ApiModelProperty("영업시간")
    private String openedTimes;

    @Builder
    public Branchs(long id, String nationalAuthemsCode, String statsCode, String citysCode, String visabled,
        String nameEng, String name, String pickupEng, String pickup, String openedTimes) {
        super();
        this.id = id;
        this.nationalAuthemsCode = nationalAuthemsCode;
        this.statsCode = statsCode;
        this.citysCode = citysCode;
        this.visabled = visabled;
        this.nameEng = nameEng;
        this.name = name;
        this.pickupEng = pickupEng;
        this.pickup = pickup;
        this.openedTimes = openedTimes;
    }
}
