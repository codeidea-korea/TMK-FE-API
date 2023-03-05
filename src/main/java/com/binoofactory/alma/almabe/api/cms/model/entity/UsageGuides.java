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
@Table(name = "usage_guides")
@Getter
@Setter
@NoArgsConstructor
public class UsageGuides {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;

    @Column(name = "usage_guides_type_code")
    @ApiModelProperty("이용 가이드 구분 코드")
    private String usageGuidesTypeCode;

    @Column(name = "visabled")
    @ApiModelProperty("노출여부")
    private boolean visabled;

    @Column(name = "modified_at")
    @ApiModelProperty("수정 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedAt;
    
    @Column(name = "modified_user_id")
    @ApiModelProperty("수정자 일련번호")
    private long modifierId;

    @Column(name = "title")
    @ApiModelProperty("제목")
    private String title;

    @Column(name = "contents")
    @ApiModelProperty("내용")
    private String contents;

    @Column(name = "orders")
    @ApiModelProperty("정렬 순서")
    private int orders;

    @Column(name = "views")
    @ApiModelProperty("노출수")
    private int views;

    @Builder
    public UsageGuides(long id, String usageGuidesTypeCode, boolean visabled, LocalDateTime modifiedAt,
        long modifierId, String title, String contents, int orders, int views) {
        super();
        this.id = id;
        this.usageGuidesTypeCode = usageGuidesTypeCode;
        this.visabled = visabled;
        this.modifiedAt = modifiedAt;
        this.modifierId = modifierId;
        this.title = title;
        this.contents = contents;
        this.orders = orders;
        this.views = views;
    }
}
