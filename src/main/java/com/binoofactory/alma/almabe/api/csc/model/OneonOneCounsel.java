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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "oneonone_counsel")
@Getter
@Setter
@NoArgsConstructor
public class OneonOneCounsel {

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
    
    @Column(name = "reservation_id")
    @ApiModelProperty("예약 일련번호")
    private long reservationId;
    
    @ApiModelProperty("노출수")
    @Column(name = "views")
    private int views;

    @Column(name = "counsel_status_code")
    @ApiModelProperty("상담 상태 코드")
    private String counselStatusCode;

    @Column(name = "oneonone_counsel_type_code")
    @ApiModelProperty("1:1 상담 구분 코드")
    private String oneononeCounselTypeCode;
    
    @ApiModelProperty("노출여부")
    @Column(name = "visibled")
    private boolean visibled;
    
    @Column(name = "user_id")
    @ApiModelProperty("사용자 일련번호")
    private long userId;
    
    @Column(name = "brand_id")
    @ApiModelProperty("브랜드 일련번호")
    private long brandId;

    @Column(name = "counsel_case_code")
    @ApiModelProperty("상담 방식 구분 코드")
    private String counselCaseCode;

    @Column(name = "title")
    @ApiModelProperty("상담 제목")
    private String title;

    @Column(name = "contents")
    @ApiModelProperty("상담 내용")
    private String contents;
    
    @Column(name = "answer_id")
    @ApiModelProperty("답변자 일련번호")
    private long answerId;

    @Column(name = "answer")
    @ApiModelProperty("답변 내용")
    private String answer;

    @Column(name = "created_at")
    @ApiModelProperty("생성 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @ApiModelProperty("수정 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedAt;

    @Builder
    public OneonOneCounsel(long id, String userName, String userPhoneNo, String email, long reservationId, int views,
        String counselStatusCode, String oneononeCounselTypeCode, boolean visibled, long userId, long brandId,
        String counselCaseCode, String title, String contents, long answerId, String answer, LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
        super();
        this.id = id;
        this.userName = userName;
        this.userPhoneNo = userPhoneNo;
        this.email = email;
        this.reservationId = reservationId;
        this.views = views;
        this.counselStatusCode = counselStatusCode;
        this.oneononeCounselTypeCode = oneononeCounselTypeCode;
        this.visibled = visibled;
        this.userId = userId;
        this.brandId = brandId;
        this.counselCaseCode = counselCaseCode;
        this.title = title;
        this.contents = contents;
        this.answerId = answerId;
        this.answer = answer;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
