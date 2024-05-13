package org.example.controller;

import org.example.entity.ReportLogin;
import org.example.search.ElasticsearchQuery;
import org.example.search.LoginFilterModel;
import org.example.service.ReportLoginService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author a.mehdizadeh on 3/4/2024
 */
@RestController
@RequestMapping("/reportLogin")
public class ReportLoginController {

    private final ReportLoginService loginService;

    private final ElasticsearchQuery<ReportLogin> elasticsearchQuery;

    public ReportLoginController(ReportLoginService loginService, ElasticsearchQuery<ReportLogin> elasticsearchQuery) {
        this.loginService = loginService;
        this.elasticsearchQuery = elasticsearchQuery;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ReportLogin> save(@RequestBody ReportLogin reportlogin){
        return ResponseEntity.ok(loginService.save(reportlogin));
    }

    @PostMapping(value = "/find")
    public ResponseEntity<?> search(@RequestBody LoginFilterModel loginFilterModel) throws IllegalAccessException {
        return ResponseEntity.ok(elasticsearchQuery.search(loginFilterModel , ReportLogin.class ,PageRequest.of(loginFilterModel.getPageNumber() , loginFilterModel.getPageSize()) , true));
    }

    @PostMapping(value = "/findLastLoginDate")
    public ResponseEntity<?> findLastLoginDate(@RequestBody String nationalCode){
        return ResponseEntity.ok(loginService.findLastLoginByNationalCode(nationalCode));
    }

}
