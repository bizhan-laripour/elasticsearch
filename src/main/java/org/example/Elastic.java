package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

/**
 * @author a.mehdizadeh on 4/24/2024
 */
@SpringBootApplication
public class Elastic {

    private static final Logger logger = Logger.getLogger(Elastic.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(Elastic.class, args);
        logger.info("***************     ElasticSearch started         **************");
    }


    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper;
    }
}