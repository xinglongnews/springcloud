package com.sxl.springcloud;

import com.thoughtworks.xstream.converters.time.ZonedDateTimeConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.time.ZonedDateTime;

@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8002 {


    public static void main(String[] args) {

        SpringApplication.run(PaymentMain8002.class,args);
    }
}
