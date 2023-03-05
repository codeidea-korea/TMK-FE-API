package com.binoofactory.alma.almabe.api.csc.model;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "counsel")
@Data
@NoArgsConstructor
public class Counsel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;

    @Column(name = "user_name")
    @ApiModelProperty("사용자명")
    private String userName;

    @Column(name = "phone_no")
    @ApiModelProperty("사용자 전화번호")
    private String userPhoneNo;

    @Column(name = "email")
    @ApiModelProperty("이메일")
    private String email;
    
    @ApiModelProperty("노출수")
    @Column(name = "views")
    private int views;

    @Column(name = "counsel_status_code")
    @ApiModelProperty("상담 상태 코드")
    private String counselStatusCode;

    @Column(name = "counsel_type_code")
    @ApiModelProperty("상담 구분 코드")
    private String counselTypeCode;
    
    @ApiModelProperty("노출여부")
    @Column(name = "visibled")
    private boolean visibled;
    
    @Column(name = "user_id")
    @ApiModelProperty("사용자 일련번호")
    private long userId;

    @Column(name = "contents")
    @ApiModelProperty("상담 내용")
    private String contents;

    @Column(name = "created_at")
    @ApiModelProperty("생성 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;
    
    @Column(name = "modified_user_id")
    @ApiModelProperty("답변자 일련번호")
    private long modifiedId;

    @Column(name = "modified_at")
    @ApiModelProperty("수정 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedAt;

    @Builder
    public Counsel(long id, String userName, String userPhoneNo, String email, int views, String counselStatusCode,
        String counselTypeCode, boolean visibled, long userId, String contents, LocalDateTime createdAt,
        long modifiedId, LocalDateTime modifiedAt) {
        super();
        this.id = id;
        this.userName = userName;
        this.userPhoneNo = userPhoneNo;
        this.email = email;
        this.views = views;
        this.counselStatusCode = counselStatusCode;
        this.counselTypeCode = counselTypeCode;
        this.visibled = visibled;
        this.userId = userId;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedId = modifiedId;
        this.modifiedAt = modifiedAt;
    }
}
