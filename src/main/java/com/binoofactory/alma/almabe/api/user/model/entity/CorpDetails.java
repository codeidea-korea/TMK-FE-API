package com.binoofactory.alma.almabe.api.user.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "corp_details")
@Getter
@Setter
@NoArgsConstructor
public class CorpDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty("일련번호")
    private long id;
    
    @Column(name = "department_code")
    @ApiModelProperty("부서 코드")
    private String departmentCode;
    
    @Column(name = "tax_issued_code")
    @ApiModelProperty("세금 발행 구분 코드")
    private String taxIssuedCode;
    
    @Column(name = "email")
    @ApiModelProperty("회사 메일")
    private String email;
    
    @Column(name = "bank_name")
    @ApiModelProperty("은행명")
    private String bankName;
    
    @Column(name = "account_name")
    @ApiModelProperty("계좌명")
    private String accountName;
    
    @Column(name = "account_no")
    @ApiModelProperty("계좌번호")
    private String accountNo;

    @Builder
    public CorpDetails(long id, String departmentCode, String taxIssuedCode, String email, String bankName,
        String accountName, String accountNo) {
        super();
        this.id = id;
        this.departmentCode = departmentCode;
        this.taxIssuedCode = taxIssuedCode;
        this.email = email;
        this.bankName = bankName;
        this.accountName = accountName;
        this.accountNo = accountNo;
    }
}