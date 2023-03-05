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
@Table(name = "faqs")
@Getter
@Setter
@NoArgsConstructor
public class Faqs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;

    @Column(name = "faq_type_code")
    @ApiModelProperty("faq 구분 코드")
    private String faqTypeCode;

    @Column(name = "title")
    @ApiModelProperty("제목")
    private String title;

    @Column(name = "contents")
    @ApiModelProperty("내용")
    private String contents;
    
    @ApiModelProperty("정렬순서")
    @Column(name = "orders")
    private int orders;
    
    @ApiModelProperty("노출수")
    @Column(name = "views")
    private int views;
    
    @ApiModelProperty("노출여부")
    @Column(name = "visibled")
    private boolean visibled;

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
    public Faqs(long id, String faqTypeCode, String title, String contents, int orders, int views, boolean visibled,
        LocalDateTime createdAt, long modifiedId, LocalDateTime modifiedAt) {
        super();
        this.id = id;
        this.faqTypeCode = faqTypeCode;
        this.title = title;
        this.contents = contents;
        this.orders = orders;
        this.views = views;
        this.visibled = visibled;
        this.createdAt = createdAt;
        this.modifiedId = modifiedId;
        this.modifiedAt = modifiedAt;
    }
}
