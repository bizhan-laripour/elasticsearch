package org.example.entity;//package com.ansarit.sso.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.sbahmani.jalcal.util.DateException;
import org.example.util.CalenderUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import java.util.Date;

/**
 * @author a.mehdizadeh on 4/22/2024
 */
@Document(indexName = "satisfaction")
public class Satisfaction {

    @Id
    private String id;

    private String nationalCode;

    private Integer point;


    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
    private Date satisfactionDate;

    private String serviceName;

    @JsonIgnore
    private String exactDate;

    public String getId() {
        return id;
    }

    public Date getSatisfactionDate() {
        return satisfactionDate;
    }

    public void setSatisfactionDate(Date satisfactionDate) throws DateException {
        setExactDate(satisfactionDate != null ? CalenderUtil.convertJalaliDateWithFormat(CalenderUtil.gregorianToJalali(satisfactionDate)) : null);
        this.satisfactionDate = satisfactionDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getExactDate() {
        return exactDate;
    }

    public void setExactDate(String exactDate) {
        this.exactDate = exactDate;
    }
}
