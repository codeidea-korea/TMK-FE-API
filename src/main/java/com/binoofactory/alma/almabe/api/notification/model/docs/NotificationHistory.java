package com.binoofactory.alma.almabe.api.notification.model.docs;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Document
public class NotificationHistory {
    @Id
    @ApiModelProperty(value = "몽고 식별자")
    private String docId;

    @ApiModelProperty("알림 식별자")
    private long notificationId;

    @ApiModelProperty("사용자 식별자")
    private long userId;

    @ApiModelProperty("확인 여부")
    private boolean viewed;

    @ApiModelProperty("요청일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createDt;

    @Builder
    public NotificationHistory(long notificationId, long userId, boolean viewed, LocalDateTime createDt) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.viewed = viewed;
        this.createDt = createDt;
    }
}
