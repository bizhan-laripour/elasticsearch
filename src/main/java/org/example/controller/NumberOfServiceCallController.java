package org.example.controller;

import com.github.sbahmani.jalcal.util.DateException;
import org.example.search.ServiceFilterModel;
import org.example.service.NumberOfServiceCallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @author a.mehdizadeh on 5/7/2024
 */
@RestController
@RequestMapping("/numberOfServiceCall")
public class NumberOfServiceCallController {

    private final NumberOfServiceCallService numberOfServiceCallService;

    public NumberOfServiceCallController(NumberOfServiceCallService numberOfServiceCallService) {
        this.numberOfServiceCallService = numberOfServiceCallService;
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody ServiceFilterModel serviceFilterModel) throws DateException, IllegalAccessException, ParseException {
        return new ResponseEntity<>(numberOfServiceCallService.getNumberOfServiceCalls(serviceFilterModel) , HttpStatus.OK);
    }
}
