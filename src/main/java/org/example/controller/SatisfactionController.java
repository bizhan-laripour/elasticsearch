package org.example.controller;

import com.github.sbahmani.jalcal.util.DateException;
import org.example.entity.Satisfaction;
import org.example.search.ElasticsearchQuery;
import org.example.search.SatisfactionFilterModel;
import org.example.service.SatisfactionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author a.mehdizadeh on 4/22/2024
 */
@RestController
@RequestMapping(value = "/satisfaction")
public class SatisfactionController {


    private final SatisfactionService satisfactionService;
    private final ElasticsearchQuery<Satisfaction> elasticsearchQuery;

    public SatisfactionController(SatisfactionService satisfactionService, ElasticsearchQuery<Satisfaction> elasticsearchQuery) {
        this.satisfactionService = satisfactionService;
        this.elasticsearchQuery = elasticsearchQuery;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveSatisfaction(@RequestBody Satisfaction satisfaction) throws DateException {

        System.out.println("******   satisfaction save controller    ******");

        return ResponseEntity.ok(satisfactionService.saveSatisfaction(satisfaction));
    }


    @PostMapping(value = "/find")
    public ResponseEntity<?> search(@RequestBody SatisfactionFilterModel satisfactionFilterModel) throws IllegalAccessException {
        return ResponseEntity.ok(elasticsearchQuery.search(satisfactionFilterModel, Satisfaction.class, PageRequest.of(satisfactionFilterModel.getPageNumber(), satisfactionFilterModel.getPageSize()) , true));
    }
}
