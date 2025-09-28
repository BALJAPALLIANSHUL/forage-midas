package com.jpmc.midascore.component;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;

@Component
public class RestTemplateClient {
    private String baseURL;
    // Methods to make REST calls using RestTemplate would go here
    private RestTemplate restTemplate;


    public RestTemplateClient(RestTemplate restTemplate) {
        this.baseURL = "http://localhost:8080";
        this.restTemplate = restTemplate;
    }


    public Incentive getIncentive(Transaction transaction){
        Incentive response = restTemplate.postForObject(baseURL+"/incentive", transaction, Incentive.class);
        return response;
    }
}
