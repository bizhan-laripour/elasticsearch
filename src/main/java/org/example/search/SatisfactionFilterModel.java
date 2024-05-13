package org.example.search;

import com.github.sbahmani.jalcal.util.DateException;
import org.example.annotation.SearchField;
import org.example.enums.SearchType;
import org.example.util.CalenderUtil;

import java.text.ParseException;
import java.util.Date;

/**
 * @author a.mehdizadeh on 5/6/2024
 */
public class SatisfactionFilterModel {

    private String toDate;

    private String fromDate;

    private String nationalCode;

    private Integer point;
    @SearchField(type = SearchType.GREATER_THAN, target_field = "satisfactionDate")
    private Date fromDate2;
    @SearchField(type = SearchType.LESS_THAN, target_field = "satisfactionDate")
    private Date toDate2;
    @SearchField(type = SearchType.CONTAINS)
    private String serviceName;

    private Integer pageNumber;

    private Integer pageSize;


    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) throws DateException, ParseException {
        setToDate2(toDate != null ? CalenderUtil.jalaliToGregorian(toDate) : null);
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) throws DateException, ParseException {
        setFromDate2(fromDate != null ? CalenderUtil.jalaliToGregorian(fromDate) : null);
        this.fromDate = fromDate;
    }

    public Date getFromDate2() {
        return fromDate2;
    }

    public void setFromDate2(Date fromDate2) {
        this.fromDate2 = fromDate2;
    }

    public Date getToDate2() {
        return toDate2;
    }

    public void setToDate2(Date toDate2) {
        this.toDate2 = toDate2;
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

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
