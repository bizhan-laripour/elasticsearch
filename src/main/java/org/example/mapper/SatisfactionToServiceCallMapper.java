package org.example.mapper;

import com.github.sbahmani.jalcal.util.DateException;
import org.example.search.SatisfactionFilterModel;
import org.example.search.ServiceFilterModel;

import java.text.ParseException;

/**
 * @author a.mehdizadeh on 5/7/2024
 */
public class SatisfactionToServiceCallMapper {


    public static SatisfactionToServiceCallMapper INSTANCE = new SatisfactionToServiceCallMapper();

    private SatisfactionToServiceCallMapper() {

    }


    private static SatisfactionFilterModel toSatisfactionFilterModel(ServiceFilterModel serviceCallFilterModel) throws DateException, ParseException {
        SatisfactionFilterModel satisfactionFilterModel = new SatisfactionFilterModel();
        satisfactionFilterModel.setFromDate(serviceCallFilterModel.getFromDate() != null ? serviceCallFilterModel.getFromDate() : null);
        satisfactionFilterModel.setToDate(serviceCallFilterModel.getToDate() != null ? serviceCallFilterModel.getToDate() : null);
        satisfactionFilterModel.setServiceName(serviceCallFilterModel.getServiceName() != null ? serviceCallFilterModel.getServiceName() : null);
        return satisfactionFilterModel;
    }


    public SatisfactionFilterModel map(ServiceFilterModel serviceCallFilterModel) throws DateException, ParseException {
        return toSatisfactionFilterModel(serviceCallFilterModel);
    }






}
