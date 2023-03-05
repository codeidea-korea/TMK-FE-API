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
@Table(name = "national_authems")
@Data
@NoArgsConstructor
public class NationalAuthems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;

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
    @ApiModelProperty("국가 코드")
    private String code;

    @Builder
    public NationalAuthems(long id, boolean visabled, String nameEng, String name, String code) {
        super();
        this.id = id;
        this.visabled = visabled;
        this.nameEng = nameEng;
        this.name = name;
        this.code = code;
    }
}
