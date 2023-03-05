package com.binoofactory.alma.almabe.api.cms.model.docs;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Document
public class NewsLettersHistory {
    @Id
    @ApiModelProperty(value = "몽고 식별자")
    private String docId;

    @ApiModelProperty("브랜드 식별자")
    private long brandId;

    @ApiModelProperty("사용자 식별자")
    private long userId;

    @ApiModelProperty("사용자 이름")
    private String userName;

    @ApiModelProperty("사용자 전화번호")
    private String userPhone;

    @ApiModelProperty("사용자 이메일")
    private String userEmail;

    @ApiModelProperty("요청일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createDt;

    @ApiModelProperty("뉴스레터 상태 코드")
    private String newsLetterStatusCode;

    @Builder
    public NewsLettersHistory(long brandId, long userId, String userName, String userPhone,
        String userEmail, LocalDateTime createDt, String newsLetterStatusCode) {
        
        this.brandId = brandId;
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.createDt = createDt;
        this.newsLetterStatusCode = newsLetterStatusCode;
    }
}
