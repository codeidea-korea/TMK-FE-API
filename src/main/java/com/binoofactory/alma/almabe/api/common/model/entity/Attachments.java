package com.binoofactory.alma.almabe.api.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attachments")
@Data
@NoArgsConstructor
public class Attachments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;

    @Column(name = "contents_type")
    @ApiModelProperty("컨텐츠 구분")
    private int contentsType;

    @Column(name = "contents_id")
    @ApiModelProperty("컨텐츠 일련번호")
    private long contentsId;

    @Column(name = "url")
    @ApiModelProperty("리소스 파일 경로")
    private String url;

    @Builder
    public Attachments(long id, int contentsType, long contentsId, String url) {
        this.id = id;
        this.contentsType = contentsType;
        this.contentsId = contentsId;
        this.url = url;
    }
}
