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
@Table(name = "popups")
@Getter
@Setter
@NoArgsConstructor
public class Popups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;

    @Column(name = "popups_type_code")
    @ApiModelProperty("팝업 구분 코드")
    private String popupsTypeCode;

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

    @Column(name = "title")
    @ApiModelProperty("제목")
    private String title;

    @Column(name = "contents")
    @ApiModelProperty("내용")
    private String contents;

    @Column(name = "fixed")
    @ApiModelProperty("고정여부")
    private boolean fixed;

    @Column(name = "efective_started_at")
    @ApiModelProperty("시작 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime efectiveStartedAt;

    @Column(name = "efective_ended_at")
    @ApiModelProperty("종료 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime efectiveEndedAt;

    @Column(name = "check_started_at")
    @ApiModelProperty("확인 시작 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime checkStartedAt;

    @Column(name = "check_ended_at")
    @ApiModelProperty("확인 종료 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime checkEndedAt;

    @Builder
    public Popups(long id, String popupsTypeCode, int orders, LocalDateTime modifiedAt, long modifierId,
        boolean visabled, String title, String contents, boolean fixed, LocalDateTime efectiveStartedAt,
        LocalDateTime efectiveEndedAt, LocalDateTime checkStartedAt, LocalDateTime checkEndedAt) {
        super();
        this.id = id;
        this.popupsTypeCode = popupsTypeCode;
        this.orders = orders;
        this.modifiedAt = modifiedAt;
        this.modifierId = modifierId;
        this.visabled = visabled;
        this.title = title;
        this.contents = contents;
        this.fixed = fixed;
        this.efectiveStartedAt = efectiveStartedAt;
        this.efectiveEndedAt = efectiveEndedAt;
        this.checkStartedAt = checkStartedAt;
        this.checkEndedAt = checkEndedAt;
    }
}
