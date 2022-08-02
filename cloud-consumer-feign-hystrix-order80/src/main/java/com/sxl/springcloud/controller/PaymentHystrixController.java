package com.sxl.springcloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sxl.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "payment_GlobalMehtodHander")
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String   payment_info(@PathVariable("id") Integer id){

       String result =paymentHystrixService.payment_info(id);

       return result;
    }


    @GetMapping("/payment/hystrix/timeout1/{id}")
//    @HystrixCommand(fallbackMethod = "payment_MethodHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")
//    })
    @HystrixCommand
    public String  payment_info1(@PathVariable("id") Integer id){

        String result=paymentHystrixService.payment_info1(id);

        return result;
    }


    public String  payment_MethodHandler(Integer id){


        return Thread.currentThread().getName()+"服务发生故障 ";
    }


    public String  payment_GlobalMehtodHander(){

        return "Global服务异常";
    }
}
