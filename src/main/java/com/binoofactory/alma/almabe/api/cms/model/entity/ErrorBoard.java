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
@Table(name = "error_board")
@Getter
@Setter
@NoArgsConstructor
public class ErrorBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "brand_id")
    @ApiModelProperty("브랜드 일련번호")
    private long brandId;

    @Column(name = "large_category")
    @ApiModelProperty("대분류")
    private String largeCategory;

    @Column(name = "medium_category")
    @ApiModelProperty("중분류")
    private String mediumCategory;

    @Column(name = "small_category")
    @ApiModelProperty("소분류")
    private String smallCategory;

    @Column(name = "contents")
    @ApiModelProperty("내용")
    private String contents;

    @Column(name = "checked")
    @ApiModelProperty("확인 여부")
    private boolean checked;

    @Column(name = "checker_id")
    @ApiModelProperty("확인 사용자 식별번호")
    private long checkerId;

    @Column(name = "created_at")
    @ApiModelProperty("예약 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @Builder
    public ErrorBoard(long id, long brandId, String largeCategory, String mediumCategory, String smallCategory,
        String contents, boolean checked, long checkerId, LocalDateTime createdAt) {
        super();
        this.id = id;
        this.brandId = brandId;
        this.largeCategory = largeCategory;
        this.mediumCategory = mediumCategory;
        this.smallCategory = smallCategory;
        this.contents = contents;
        this.checked = checked;
        this.checkerId = checkerId;
        this.createdAt = createdAt;
    }
}
