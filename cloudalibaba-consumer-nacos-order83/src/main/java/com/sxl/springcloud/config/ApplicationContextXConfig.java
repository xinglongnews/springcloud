package com.sxl.springcloud.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextXConfig {

    @Bean
    @LoadBalanced
    public RestTemplate  getRestTempete(){


        return new RestTemplate();
    }
}
