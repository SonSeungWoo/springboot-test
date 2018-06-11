package com.ssw.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteServiceImpl implements RemoteService {

    RestTemplate restTemplate;

    public RemoteServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String getName() {
        return "Remote Service";
    }

    public int getNumber(){
        return 100;
    }

    public String callRestService() {
        return restTemplate.getForObject("/person",String.class);
    }
}
