package org.example.service;

import com.github.sbahmani.jalcal.util.DateException;
import org.example.entity.Satisfaction;
import org.example.repository.SatisfactionRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author a.mehdizadeh on 4/22/2024
 */
@Service
public class SatisfactionService {

    private final SatisfactionRepository satisfactionRepository;

    public SatisfactionService(SatisfactionRepository satisfactionRepository) {
        this.satisfactionRepository = satisfactionRepository;
    }

    public Satisfaction saveSatisfaction(Satisfaction satisfaction) throws DateException {
        satisfaction.setSatisfactionDate(new Date());
        return satisfactionRepository.save(satisfaction);
    }

}
