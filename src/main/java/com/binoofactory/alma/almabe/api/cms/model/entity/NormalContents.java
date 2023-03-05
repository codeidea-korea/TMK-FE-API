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
@Table(name = "normal_contents")
@Getter
@Setter
@NoArgsConstructor
public class NormalContents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;

    @Column(name = "normal_contents_exhibition_type_code")
    @ApiModelProperty("일반 컨텐츠 전시 구분 코드")
    private String normalContentsExhibitionTypeCode;

    @Column(name = "orders")
    @ApiModelProperty("정렬 순서")
    private int orders;

    @Column(name = "modified_at")
    @ApiModelProperty("수정 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedAt;
    
    @Column(name = "modified_user_id")
    @ApiModelProperty("수정자 일련번호")
    private long modifierId;

    @Column(name = "visabled")
    @ApiModelProperty("노출여부")
    private boolean visabled;

    @Column(name = "contents")
    @ApiModelProperty("내용")
    private String contents;

    @Builder
    public NormalContents(long id, String normalContentsExhibitionTypeCode, int orders, LocalDateTime modifiedAt,
        long modifierId, boolean visabled, String contents) {
        super();
        this.id = id;
        this.normalContentsExhibitionTypeCode = normalContentsExhibitionTypeCode;
        this.orders = orders;
        this.modifiedAt = modifiedAt;
        this.modifierId = modifierId;
        this.visabled = visabled;
        this.contents = contents;
    }
}
