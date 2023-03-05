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
@Table(name = "main_contents")
@Getter
@Setter
@NoArgsConstructor
public class MainContents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;

    @Column(name = "main_contents_type_code")
    @ApiModelProperty("메인컨텐츠 구분 코드")
    private String mainContentsTypeCode;

    @Column(name = "orders")
    @ApiModelProperty("정렬 순서")
    private int orders;
    
    @Column(name = "modified_user_id")
    @ApiModelProperty("최종 수정자 일련번호")
    private long modifiedId;

    @Column(name = "modified_at")
    @ApiModelProperty("수정 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedAt;

    @Builder
    public MainContents(long id, String mainContentsTypeCode, int orders, long modifiedId, LocalDateTime modifiedAt) {
        super();
        this.id = id;
        this.mainContentsTypeCode = mainContentsTypeCode;
        this.orders = orders;
        this.modifiedId = modifiedId;
        this.modifiedAt = modifiedAt;
    }
}
