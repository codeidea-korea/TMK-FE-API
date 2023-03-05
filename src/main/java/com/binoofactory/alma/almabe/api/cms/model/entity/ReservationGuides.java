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
@Table(name = "reservation_guides")
@Getter
@Setter
@NoArgsConstructor
public class ReservationGuides {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;

    @Column(name = "reservations_exhibition_type_code")
    @ApiModelProperty("예약 전시 구분 코드")
    private String reservationsExhibitionTypeCode;

    @Column(name = "reservation_guide_code")
    @ApiModelProperty("예약 가이드 코드")
    private String reservationGuideCode;

    @Column(name = "payment_type_code")
    @ApiModelProperty("결제 구분코드")
    private String paymentTypeCode;

    @Column(name = "modified_at")
    @ApiModelProperty("수정 일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedAt;
    
    @Column(name = "modified_user_id")
    @ApiModelProperty("수정자 일련번호")
    private long modifierId;

    @Column(name = "contents")
    @ApiModelProperty("내용")
    private String contents;

    @Column(name = "visabled")
    @ApiModelProperty("노출여부")
    private boolean visabled;

    @Builder
    public ReservationGuides(long id, String reservationsExhibitionTypeCode, String reservationGuideCode,
        String paymentTypeCode, LocalDateTime modifiedAt, long modifierId, String contents, boolean visabled) {
        super();
        this.id = id;
        this.reservationsExhibitionTypeCode = reservationsExhibitionTypeCode;
        this.reservationGuideCode = reservationGuideCode;
        this.paymentTypeCode = paymentTypeCode;
        this.modifiedAt = modifiedAt;
        this.modifierId = modifierId;
        this.contents = contents;
        this.visabled = visabled;
    }
}
