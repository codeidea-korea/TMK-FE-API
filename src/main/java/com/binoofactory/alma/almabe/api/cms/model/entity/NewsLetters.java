package com.binoofactory.alma.almabe.api.cms.model.entity;

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
@Table(name = "news_letters")
@Getter
@Setter
@NoArgsConstructor
public class NewsLetters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "brand_id")
    @ApiModelProperty("브랜드 일련번호")
    private long brandId;
    
    @Column(name = "user_id")
    @ApiModelProperty("사용자 일련번호")
    private long userId;

    @Column(name = "user_name")
    @ApiModelProperty("사용자 이름")
    private String userName;

    @Column(name = "user_phone")
    @ApiModelProperty("사용자 전화번호")
    private String userPhone;

    @Column(name = "user_email")
    @ApiModelProperty("사용자 이메일")
    private String userEmail;

    @Column(name = "created_at")
    @ApiModelProperty("생성 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @Column(name = "last_sended_at")
    @ApiModelProperty("마지막 전송 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastSendedAt;

    @Column(name = "newsletter_status_code")
    @ApiModelProperty("뉴스레터 상태 코드")
    private String newsletterStatusCode;

    @Builder
    public NewsLetters(long id, long brandId, long userId, String userName, String userPhone, String userEmail,
        LocalDateTime createdAt, LocalDateTime lastSendedAt, String newsletterStatusCode) {
        super();
        this.id = id;
        this.brandId = brandId;
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
        this.lastSendedAt = lastSendedAt;
        this.newsletterStatusCode = newsletterStatusCode;
    }
}
