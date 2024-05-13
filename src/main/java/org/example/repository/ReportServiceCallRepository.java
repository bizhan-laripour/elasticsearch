package org.example.repository;


import org.example.entity.ReportServiceCall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author a.mehdizadeh on 3/4/2024
 */
@Repository
public interface ReportServiceCallRepository extends ElasticsearchRepository<ReportServiceCall, String> {

    Page<ReportServiceCall> findAll(Pageable pageable);
    List<ReportServiceCall> findAllByServiceName(String serviceName);
    List<ReportServiceCall> findAll();
    List<ReportServiceCall> findAllByServiceDate(Date serviceDate);

//    Page<ReportServiceCall> findAllByTimeAfter(Date time, Pageable pageable);
}


