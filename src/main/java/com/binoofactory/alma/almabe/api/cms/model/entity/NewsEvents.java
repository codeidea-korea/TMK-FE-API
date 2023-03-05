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
@Table(name = "news_events")
@Getter
@Setter
@NoArgsConstructor
public class NewsEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;

    @Column(name = "news_contents_type_code")
    @ApiModelProperty("뉴스컨텐츠 구분 코드")
    private String newsContentsTypeCode;

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

    @Builder
    public NewsEvents(long id, String newsContentsTypeCode, int orders, long modifiedId, LocalDateTime modifiedAt,
        boolean visabled, String title, String contents, boolean fixed, LocalDateTime efectiveStartedAt,
        LocalDateTime efectiveEndedAt) {
        super();
        this.id = id;
        this.newsContentsTypeCode = newsContentsTypeCode;
        this.orders = orders;
        this.modifiedId = modifiedId;
        this.modifiedAt = modifiedAt;
        this.visabled = visabled;
        this.title = title;
        this.contents = contents;
        this.fixed = fixed;
        this.efectiveStartedAt = efectiveStartedAt;
        this.efectiveEndedAt = efectiveEndedAt;
    }
}
