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
@Table(name = "main_contents_detail")
@Getter
@Setter
@NoArgsConstructor
public class MainContentsDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "main_contents_id")
    @ApiModelProperty("메인 컨텐츠 일련번호")
    private long mainContentsId;

    @Column(name = "menu_name")
    @ApiModelProperty("메인컨텐츠 메뉴명")
    private String menuName;

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

    @Column(name = "orders")
    @ApiModelProperty("정렬 순서")
    private int orders;

    @Column(name = "image")
    @ApiModelProperty("image")
    private String image;

    @Column(name = "url")
    @ApiModelProperty("url")
    private String url;

    @Column(name = "alt")
    @ApiModelProperty("alt")
    private String alt;

    @Builder
    public MainContentsDetail(long id, long mainContentsId, String menuName, LocalDateTime efectiveStartedAt,
        LocalDateTime efectiveEndedAt, int orders, String image, String url, String alt) {
        super();
        this.id = id;
        this.mainContentsId = mainContentsId;
        this.menuName = menuName;
        this.efectiveStartedAt = efectiveStartedAt;
        this.efectiveEndedAt = efectiveEndedAt;
        this.orders = orders;
        this.image = image;
        this.url = url;
        this.alt = alt;
    }
}
