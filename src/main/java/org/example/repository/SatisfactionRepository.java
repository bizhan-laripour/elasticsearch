package org.example.repository;

import org.example.entity.Satisfaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author a.mehdizadeh on 4/22/2024
 */
@Repository
public interface SatisfactionRepository extends ElasticsearchRepository<Satisfaction, String> {

    List<Satisfaction> findAllByNationalCode(String nationalCode , Pageable pageable);
    List<Satisfaction> findAllByPoint(Integer point , Pageable pageable);
    List<Satisfaction> findAllByServiceName(String serviceName);
    List<Satisfaction> findAll();
    List<Satisfaction> findAllBySatisfactionDate(Date satisfactionDate);
}
