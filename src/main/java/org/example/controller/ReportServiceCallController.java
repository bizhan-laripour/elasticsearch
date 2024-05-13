package org.example.controller;

import com.github.sbahmani.jalcal.util.DateException;
import org.example.dto.ServiceNameDto;
import org.example.entity.ReportServiceCall;
import org.example.search.ElasticsearchQuery;
import org.example.search.ServiceFilterModel;
import org.example.service.ReportServiceCallService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author a.mehdizadeh on 3/4/2024
 */
@RestController
@RequestMapping(value = "/serviceCall")
public class ReportServiceCallController {


    private final ReportServiceCallService serviceCallService;
    private final ElasticsearchQuery<ReportServiceCall> elasticsearchQuery;

    public ReportServiceCallController(ReportServiceCallService serviceCallService, ElasticsearchQuery<ReportServiceCall> elasticsearchQuery) {
        this.serviceCallService = serviceCallService;
        this.elasticsearchQuery = elasticsearchQuery;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ReportServiceCall> save(@RequestBody ReportServiceCall reportServiceCall) throws DateException {
        List<ServiceNameDto> serviceNameDtos = serviceNames();
        ServiceNameDto serviceNameDto = serviceNameDtos.stream().filter(obj -> obj.getServiceName().equals(reportServiceCall.getServiceName())).findFirst().get();
        reportServiceCall.setServiceName(serviceNameDto.getServiceNameEn());
        return ResponseEntity.ok(serviceCallService.save(reportServiceCall));
    }

    @PostMapping(value = "/find")
    public ResponseEntity<?> search(@RequestBody ServiceFilterModel serviceFilterModel) throws IllegalAccessException {
        return ResponseEntity.ok(elasticsearchQuery.search(serviceFilterModel, ReportServiceCall.class, PageRequest.of(serviceFilterModel.getPageNumber(), serviceFilterModel.getPageSize()) , true));
    }

    @GetMapping(value = "/serviceNames")
    public List<ServiceNameDto> serviceNames(){
        return serviceCallService.serviceNames();
    }
}


