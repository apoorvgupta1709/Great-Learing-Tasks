package com.cspl.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BeanDefinition {

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }


    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
