package com.binoofactory.alma.almabe.api.common.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "common_group_code")
@Data
@NoArgsConstructor
public class CommonGroupCode {
    
    @Id
    @Column(name = "group_code")
    @ApiModelProperty("그룹 코드")
    private String groupCode;

    @Column(name = "group_name")
    @ApiModelProperty("그룹 코드명")
    private String name;

    @Column(name = "used")
    @ApiModelProperty("사용여부")
    private boolean used;

    @Column(name = "created_at")
    @ApiModelProperty("생성 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @Column(name = "modifier_id")
    @ApiModelProperty("최종 수정자 일련번호")
    private long modifiedId;

    @Column(name = "modified_at")
    @ApiModelProperty("수정 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedAt;

    @Builder
    public CommonGroupCode(String groupCode, String name, boolean used, LocalDateTime createdAt, long modifiedId,
        LocalDateTime modifiedAt) {
        super();
        this.groupCode = groupCode;
        this.name = name;
        this.used = used;
        this.createdAt = createdAt;
        this.modifiedId = modifiedId;
        this.modifiedAt = modifiedAt;
    }
}
