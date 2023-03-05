package com.binoofactory.alma.almabe.api.user.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Entity
@Table(name = "travel_corps")
@Data
@NoArgsConstructor
public class TravelCorps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("여행사 PK")
    private long id;
    
    @Column(name = "travel_no")
    @ApiModelProperty("여행사 연동 번호")
    private String travelNo;
    
    @Column(name = "business_no")
    @ApiModelProperty("여행사 사업자 번호")
    private String businessNo;
    
    @Column(name = "name")
    @ApiModelProperty("여행사명")
    private String name;
    
    @Column(name = "director_name")
    @ApiModelProperty("여행사 담당자 이름")
    private String directorName;
    
    @Column(name = "director_phone_no")
    @ApiModelProperty("여행사 담당자 전화번호")
    private String directorPhoneNo;
    
    @Column(name = "fee_rate")
    @ApiModelProperty("수수료율")
    private int fee_rate;

    @Builder
    public TravelCorps(long id, String travelNo, String businessNo, String name, String directorName,
            String directorPhoneNo, int fee_rate) {
        super();

        this.id = id;
        this.travelNo = travelNo;
        this.businessNo = businessNo;
        this.name = name;
        this.directorName = directorName;
        this.directorPhoneNo = directorPhoneNo;
        this.fee_rate = fee_rate;
    }

}