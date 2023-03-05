package com.binoofactory.alma.almabe.api.feecontract.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.binoofactory.alma.almabe.api.feecontract.data.OptionType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "service_options")
@Getter
@Setter
@NoArgsConstructor
public class ServiceOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "service_id")
    @ApiModelProperty("서비스 일련번호")
    private long serviceId;
    
    @Column(name = "option_type")
    @ApiModelProperty("서비스 옵션 구분")
    private OptionType optionType;

    @Column(name = "option_code")
    @ApiModelProperty("서비스 옵션 코드")
    private String optionCode;
    
    @Column(name = "used")
    @ApiModelProperty("사용여부")
    private boolean used;

    @Builder
    public ServiceOptions(long id, long serviceId, OptionType optionType, String optionCode, boolean used) {
        super();
        this.id = id;
        this.serviceId = serviceId;
        this.optionType = optionType;
        this.optionCode = optionCode;
        this.used = used;
    }
}
