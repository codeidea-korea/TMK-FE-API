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
@Table(name = "user_auth")
@Getter
@Setter
@NoArgsConstructor
public class UserAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "auth_id")
    @ApiModelProperty("권한 식별번호")
    private long authId;
    
    @Column(name = "user_id")
    @ApiModelProperty("사용자 식별번호")
    private long userId;

    @Builder
    public UserAuth(long id, long authId, long userId) {
        super();
        this.id = id;
        this.authId = authId;
        this.userId = userId;
    }
}
