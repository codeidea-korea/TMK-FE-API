package com.binoofactory.alma.almabe.api.user.model.entity;

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
@Table(name = "auth")
@Getter
@Setter
@NoArgsConstructor
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "code")
    @ApiModelProperty("(구) 권한 코드")
    private int code;
    
    @Column(name = "group_code")
    @ApiModelProperty("(구) 권한 그룹 코드")
    private String groupCode;
    
    @Column(name = "name")
    @ApiModelProperty("(구) 권한 명")
    private String name;

    @Builder
    public Auth(long id, int code, String groupCode, String name) {
        super();
        this.id = id;
        this.code = code;
        this.groupCode = groupCode;
        this.name = name;
    }
}