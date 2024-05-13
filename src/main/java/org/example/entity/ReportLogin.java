package org.example.entity;//package com.ansarit.sso.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.example.util.CalenderUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author a.mehdizadeh on 3/4/2024
 */
@Document(indexName = "report-login")
public class ReportLogin {

    @Id
    @JsonIgnore
    private String id;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
    @JsonIgnore
    private Date loginDate;

    private String StringLoginDate;

    private String message;

    private String ip;

    private String OS;

    private String nationalCode;

    private String firstName;

    private String lastName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLoginDate() {

        return loginDate;
    }

    public String getStringLoginDate() {
        return StringLoginDate;
    }

    public void setStringLoginDate(String stringLoginDate) {
        StringLoginDate = stringLoginDate;
    }

    public void setLoginDate(Date loginDate) {
        setStringLoginDate(CalenderUtil.gregorianToJalali(loginDate));
        this.loginDate = loginDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

