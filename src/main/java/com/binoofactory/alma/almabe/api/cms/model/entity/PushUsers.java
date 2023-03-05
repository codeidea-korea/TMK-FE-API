package com.binoofactory.alma.almabe.api.cms.model.entity;

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
@Table(name = "push_users")
@Getter
@Setter
@NoArgsConstructor
public class PushUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "brand_id")
    @ApiModelProperty("브랜드 일련번호")
    private long brandId;

    @Column(name = "brand_name")
    @ApiModelProperty("브랜드명")
    private String brandName;
    
    @Column(name = "user_id")
    @ApiModelProperty("사용자 일련번호")
    private long userId;

    @Column(name = "user_uid")
    @ApiModelProperty("사용자 로그인 아이디")
    private String userUid;

    @Builder
    public PushUsers(long id, long brandId, String brandName, long userId, String userUid) {
        super();
        this.id = id;
        this.brandId = brandId;
        this.brandName = brandName;
        this.userId = userId;
        this.userUid = userUid;
    }
}
