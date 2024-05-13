package org.example.repository;

import org.example.entity.ReportLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author a.mehdizadeh on 3/4/2024
 */
@Repository
public interface ReportLoginRepository extends ElasticsearchRepository<ReportLogin, String>  {

    Page<ReportLogin> findAll(Pageable pageable);

//    Page<ReportLogin> findAllByTimesAfter(Date date, Pageable pageable);

    Page<ReportLogin> findAllByNationalCode(String nationalCode, Pageable pageable);

//    Long countDistinctByNationalCode();
    List<ReportLogin> findByNationalCode(String nationalCode);
}


