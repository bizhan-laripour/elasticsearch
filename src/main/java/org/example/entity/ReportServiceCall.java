package org.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.sbahmani.jalcal.util.DateException;
import org.example.util.CalenderUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;

/**
 * @author a.mehdizadeh on 3/4/2024
 */
@Document(indexName = "service-call")
public class ReportServiceCall {

    @Id
    @JsonIgnore
    private String id;


    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
    private Date serviceDate;

    private String stringServiceDate;

    private String message;

    private String nationalCode;

    private String serviceName;

    private String seviceNameFa;

    private String exactDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) throws DateException {
        setStringServiceDate(CalenderUtil.gregorianToJalali(serviceDate));
        setExactDate(serviceDate != null ? CalenderUtil.convertJalaliDateWithFormat(CalenderUtil.gregorianToJalali(serviceDate)) : null);

        this.serviceDate = serviceDate;
    }

    public String getExactDate() {
        return exactDate;
    }

    public void setExactDate(String exactDate) {
        this.exactDate = exactDate;
    }

    public String getStringServiceDate() {
        return stringServiceDate;
    }

    public void setStringServiceDate(String stringServiceDate) {
        this.stringServiceDate = stringServiceDate;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSeviceNameFa() {
        return seviceNameFa;
    }

    public void setSeviceNameFa(String seviceNameFa) {
        this.seviceNameFa = seviceNameFa;
    }
}


