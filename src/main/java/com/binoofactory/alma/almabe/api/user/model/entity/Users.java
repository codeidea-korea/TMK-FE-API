package com.binoofactory.alma.almabe.api.user.model.entity;

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

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@ToString
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "brand_id")
    private long brandId;

    @Column(name = "signup_case_code")
    private String signupCaseCode;

    @Column(name = "user_status_code")
    private String userStatusCode;

    @Column(name = "allowed_marketting_code")
    private String allowedMarkettingCode;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "first_name_eng")
    private String firstNameEng;

    @Column(name = "last_name_eng")
    private String lastNameEng;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "mobile_phone_no")
    private String mobilePhoneNo;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;
    
    @Column(name = "travel_corp_id")
    private long travelCorpId;
    
    @Column(name = "corp_detail_id")
    private long corpDetailId;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "deleted_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime deletedAt;

    public Users(Users user) {
        this.id = user.id;
        this.brandId = user.brandId;
        this.signupCaseCode = user.signupCaseCode;
        this.userStatusCode = user.userStatusCode;
        this.allowedMarkettingCode = user.allowedMarkettingCode;
        this.userId = user.userId;
        this.password = user.password;
        this.name = user.name;
        this.firstNameEng = user.firstNameEng;
        this.lastNameEng = user.lastNameEng;
        this.phoneNo = user.phoneNo;
        this.mobilePhoneNo = user.mobilePhoneNo;
        this.email = user.email;
        this.birthDate = user.birthDate;
        this.createdAt = user.createdAt;
        this.travelCorpId = user.travelCorpId;
        this.corpDetailId = user.corpDetailId;
        this.deleted = user.deleted;
        this.deletedAt = user.deletedAt;
    }

    @Builder
    public Users(long id, long brandId, String signupCaseCode, String userStatusCode, String allowedMarkettingCode,
            String userId, String password, String name, String firstNameEng, String lastNameEng, String phoneNo,
            String mobilePhoneNo, String email, String birthDate, LocalDateTime createdAt, long travelCorpId,
            long corpDetailId, Boolean deleted, LocalDateTime deletedAt) {
        super();

        this.id = id;
        this.brandId = brandId;
        this.signupCaseCode = signupCaseCode;
        this.userStatusCode = userStatusCode;
        this.allowedMarkettingCode = allowedMarkettingCode;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.firstNameEng = firstNameEng;
        this.lastNameEng = lastNameEng;
        this.phoneNo = phoneNo;
        this.mobilePhoneNo = mobilePhoneNo;
        this.email = email;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.travelCorpId = travelCorpId;
        this.corpDetailId = corpDetailId;
        this.deleted = deleted;
        this.deletedAt = deletedAt;
    }

}