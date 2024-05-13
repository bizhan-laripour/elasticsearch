package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

/**
 * @author a.mehdizadeh on 4/24/2024
 */
@Configuration
public class ElasticsearchConfig extends ElasticsearchConfiguration {

    private final Environment environment;



    public ElasticsearchConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder().connectedTo(environment.getProperty("elasticsearch.host")).build();
    }


}
