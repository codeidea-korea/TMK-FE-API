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
@Table(name = "notice")
@Getter
@Setter
@NoArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "brand_id")
    @ApiModelProperty("브랜드 일련번호")
    private long brandId;

    @Column(name = "title")
    @ApiModelProperty("제목")
    private String title;

    @Column(name = "contents")
    @ApiModelProperty("내용")
    private String contents;

    @Column(name = "visibled")
    @ApiModelProperty("노출여부")
    private boolean visabled;

    @Column(name = "fixied")
    @ApiModelProperty("고정여부")
    private boolean fixed;

    @Column(name = "modified_user_id")
    @ApiModelProperty("최종 수정자 일련번호")
    private long modifiedId;

    @Column(name = "modified_at")
    @ApiModelProperty("수정 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedAt;

    @Column(name = "views")
    @ApiModelProperty("노출수")
    private int views;

    @Builder
    public Notice(long id, long brandId, String title, String contents, boolean visabled, boolean fixed,
        long modifiedId, LocalDateTime modifiedAt, int views) {
        super();
        this.id = id;
        this.brandId = brandId;
        this.title = title;
        this.contents = contents;
        this.visabled = visabled;
        this.fixed = fixed;
        this.modifiedId = modifiedId;
        this.modifiedAt = modifiedAt;
        this.views = views;
    }
}
