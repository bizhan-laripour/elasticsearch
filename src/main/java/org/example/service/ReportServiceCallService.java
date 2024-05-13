package org.example.service;

import com.github.sbahmani.jalcal.util.DateException;
import org.example.dto.ServiceNameDto;
import org.example.entity.ReportServiceCall;
import org.example.enums.ServiceName;
import org.example.repository.ReportServiceCallRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author a.mehdizadeh on 3/4/2024
 */
@Service
public class ReportServiceCallService {

    private final ReportServiceCallRepository reportserviceCallRepository;

    public ReportServiceCallService(ReportServiceCallRepository serviceCallRepository) {
        this.reportserviceCallRepository = serviceCallRepository;
    }

    public ReportServiceCall save(ReportServiceCall reportServiceCall) throws DateException {
        return reportserviceCallRepository.save(getCurrentDate(reportServiceCall));
    }

    private ReportServiceCall getCurrentDate(ReportServiceCall reportServiceCall) throws DateException {
        Date date = new Date();
        reportServiceCall.setServiceDate(date);
        return reportServiceCall;
    }

    public List<ServiceNameDto> serviceNames() {
        ServiceNameDto serviceNameDto;
        ServiceName[] all = ServiceName.values();
        List<ServiceNameDto> result = new ArrayList<>();
        for (ServiceName serviceName : all) {
            serviceNameDto = new ServiceNameDto();
            serviceNameDto.setServiceName(serviceName.getValue());
            serviceNameDto.setServiceNameFa(serviceName.getTitle());
            serviceNameDto.setServiceNameEn(serviceName.name());
            result.add(serviceNameDto);
        }
        return result;
    }

}


