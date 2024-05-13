package org.example.search;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.sbahmani.jalcal.util.DateException;
import org.example.annotation.SearchField;
import org.example.enums.SearchType;
import org.example.util.CalenderUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author a.mehdizadeh on 5/5/2024
 */
public class LoginFilterModel {

    private String toDate;

    private String fromDate;

    private List<String> betweenDates;

    private int pageSize;

    private int pageNumber;

    @SearchField(type = SearchType.LESS_THAN, target_field = "loginDate")
    @JsonIgnore
    private Date toDate2;

    @SearchField(type = SearchType.GREATER_THAN, target_field = "loginDate")
    @JsonIgnore
    private Date fromDate2;

    @SearchField(type = SearchType.BETWEEN, target_field = "loginDate")
    @JsonIgnore
    private List<Date> betweenDates2;

    @SearchField(type = SearchType.CONTAINS)
    private String message;

    @SearchField(type = SearchType.EQUAL_TO)
    private String ip;

    @SearchField(type = SearchType.EQUAL_TO)
    private String OS;

    @SearchField(type = SearchType.EQUAL_TO)
    private String nationalCode;

    @SearchField(type = SearchType.CONTAINS)
    private String firstName;

    @SearchField(type = SearchType.CONTAINS)
    private String lastName;


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) throws DateException, ParseException {
        setToDate2(CalenderUtil.jalaliToGregorian(toDate));
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) throws DateException, ParseException {
        setFromDate2(CalenderUtil.jalaliToGregorian(fromDate));
        this.fromDate = fromDate;
    }

    public List<String> getBetweenDates() {
        return betweenDates;
    }

    public void setBetweenDates(List<String> betweenDates) {
        this.betweenDates.forEach(obj -> {
            try {
                betweenDates2.add(CalenderUtil.jalaliToGregorian(obj));
            } catch (DateException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
        this.betweenDates = betweenDates;
    }

    public Date getToDate2() {
        return toDate2;
    }

    public void setToDate2(Date toDate2) {
        this.toDate2 = toDate2;
    }

    public Date getFromDate2() {
        return fromDate2;
    }

    public void setFromDate2(Date fromDate2) {
        this.fromDate2 = fromDate2;
    }

    public List<Date> getBetweenDates2() {
        return betweenDates2;
    }

    public void setBetweenDates2(List<Date> betweenDates2) {
        this.betweenDates2 = betweenDates2;
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
