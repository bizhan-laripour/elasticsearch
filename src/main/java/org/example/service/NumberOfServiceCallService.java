package org.example.service;

import com.github.sbahmani.jalcal.util.DateException;
import org.example.dto.NumberOfServiceCallDto;
import org.example.dto.ServiceNameDto;
import org.example.entity.ReportServiceCall;
import org.example.entity.Satisfaction;
import org.example.mapper.SatisfactionToServiceCallMapper;
import org.example.search.ElasticsearchQuery;
import org.example.search.ResponseGenerator;
import org.example.search.SatisfactionFilterModel;
import org.example.search.ServiceFilterModel;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author a.mehdizadeh on 5/7/2024
 */
@Service
public class NumberOfServiceCallService {

    private final ReportServiceCallService reportServiceCallService;
    private final ElasticsearchQuery<ReportServiceCall> reportServiceCallElasticsearchQuery;
    private final ElasticsearchQuery<Satisfaction> satisfactionElasticsearchQuery;

    public NumberOfServiceCallService(ReportServiceCallService reportServiceCallService, ElasticsearchQuery<ReportServiceCall> reportServiceCallElasticsearchQuery, ElasticsearchQuery<Satisfaction> satisfactionElasticsearchQuery) {
        this.reportServiceCallService = reportServiceCallService;
        this.reportServiceCallElasticsearchQuery = reportServiceCallElasticsearchQuery;
        this.satisfactionElasticsearchQuery = satisfactionElasticsearchQuery;

    }


    public List<NumberOfServiceCallDto> getNumberOfServiceCalls(ServiceFilterModel serviceCallFilterModel) throws IllegalAccessException, DateException, ParseException {
        serviceCallFilterModel.setServiceName(serviceCallFilterModel.getServiceName() != null ? getServiceNameEn(serviceCallFilterModel.getServiceName()) : serviceCallFilterModel.getServiceName());
        ResponseGenerator<ReportServiceCall> serviceCallsGenerator = reportServiceCallElasticsearchQuery.search(serviceCallFilterModel, ReportServiceCall.class, null, false);
        List<ReportServiceCall> serviceCalls = serviceCallsGenerator.getObject();
        SatisfactionFilterModel satisfactionFilterModel = SatisfactionToServiceCallMapper.INSTANCE.map(serviceCallFilterModel);
        ResponseGenerator<Satisfaction> satisfactionGenerator = satisfactionElasticsearchQuery.search(satisfactionFilterModel, Satisfaction.class, null, false);
        List<Satisfaction> satisfactions = satisfactionGenerator.getObject();
        List<NumberOfServiceCallDto> numberOfServiceCallDtos = populate(serviceCalls, satisfactions);
        if (serviceCallFilterModel.getServiceName() != null) {
            return numberOfServiceCallDtos.stream().filter(obj -> obj.getServiceNameEn().equals(serviceCallFilterModel.getServiceName())).toList();
        }
        return populate(serviceCalls, satisfactions);
    }


    private List<NumberOfServiceCallDto> populate(List<ReportServiceCall> serviceCalls, List<Satisfaction> satisfactions) {
        List<NumberOfServiceCallDto> result = getAverage(satisfactions);
        result.forEach(numberOfServiceCallDto -> {
            Integer size = serviceCalls.stream().filter(obj -> obj.getServiceName().equals(numberOfServiceCallDto.getServiceNameEn())).toList().size();
            numberOfServiceCallDto.setNumberOfCalls(size);
        });
        return result;

    }

    private List<NumberOfServiceCallDto> getAverage(List<Satisfaction> satisfactions) {
        List<ServiceNameDto> getServiceNames = reportServiceCallService.serviceNames();
        List<NumberOfServiceCallDto> result = new ArrayList<>();
        getServiceNames.forEach(serviceName -> {
            NumberOfServiceCallDto numberOfServiceCallDto = new NumberOfServiceCallDto();
            AtomicInteger sum = new AtomicInteger();
            List<Integer> satisfaction = satisfactions.stream().filter(obj -> obj.getServiceName().equals(serviceName.getServiceNameEn())).map(Satisfaction::getPoint).toList();
            satisfaction.forEach(sum::addAndGet);
            numberOfServiceCallDto.setServiceNameEn(serviceName.getServiceNameEn());
            numberOfServiceCallDto.setServiceNameFa(serviceName.getServiceNameFa());
            numberOfServiceCallDto.setAveragePoints(populateAverage(satisfaction.size(), sum.get()));
            result.add(numberOfServiceCallDto);
        });
        return result;
    }


    private double populateAverage(Integer size, Integer sum) {
        if (size != 0) {
            return Math.floor(((double) sum / size) * 10) / 10;
        }
        return 0;
    }

    private String getServiceNameEn(String serviceNumber) {
        List<ServiceNameDto> getServiceNames = reportServiceCallService.serviceNames();
        return getServiceNames.stream().filter(obj -> obj.getServiceName().equals(serviceNumber)).findFirst().get().getServiceNameEn();
    }

}
