package com.jpmc.midascore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    // Configuration for RestTemplate bean would go here

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }   
}
