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
@Table(name = "terms")
@Getter
@Setter
@NoArgsConstructor
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;

    @Column(name = "terms_type_code")
    @ApiModelProperty("약관 구분 코드")
    private String termsTypeCode;

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

    @Column(name = "terms_case_code")
    @ApiModelProperty("약관 타입 코드")
    private String termsCaseCode;

    @Builder
    public Terms(long id, String termsTypeCode, int orders, LocalDateTime modifiedAt, long modifierId, boolean visabled,
        String contents, String termsCaseCode) {
        super();
        this.id = id;
        this.termsTypeCode = termsTypeCode;
        this.orders = orders;
        this.modifiedAt = modifiedAt;
        this.modifierId = modifierId;
        this.visabled = visabled;
        this.contents = contents;
        this.termsCaseCode = termsCaseCode;
    }
}
