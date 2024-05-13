package org.example.dto;

/**
 * @author a.mehdizadeh on 5/7/2024
 */
public class NumberOfServiceCallDto {

    private String serviceNameEn;

    private String serviceNameFa;

    private Integer numberOfCalls;

    private Double averagePoints;

    public String getServiceNameFa() {
        return serviceNameFa;
    }

    public void setServiceNameFa(String serviceNameFa) {
        this.serviceNameFa = serviceNameFa;
    }

    public String getServiceNameEn() {
        return serviceNameEn;
    }

    public void setServiceNameEn(String serviceNameEn) {
        this.serviceNameEn = serviceNameEn;
    }

    public Integer getNumberOfCalls() {
        return numberOfCalls;
    }

    public void setNumberOfCalls(Integer numberOfCalls) {
        this.numberOfCalls = numberOfCalls;
    }

    public Double getAveragePoints() {
        return averagePoints;
    }

    public void setAveragePoints(Double averagePoints) {
        this.averagePoints = averagePoints;
    }
}
