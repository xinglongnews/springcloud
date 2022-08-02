package com.sxl.springcloud.controller;

import com.sxl.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {


    @Resource
    private PaymentService paymentService;
    @GetMapping("/payment/hystrix/ok/{id}")
    public String  payment_info(@PathVariable("id") Integer id){

        String result=paymentService.paymentInfo_ok(id);

        log.info("返回成功");
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String  payment_info1(@PathVariable("id") Integer id){

        String result=paymentService.paymentInfo_ok1(id);
        System.out.println(Thread.currentThread().getName());
        return result;
    }


    //服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String  PaymentCircuitBreaker(@PathVariable("id")Integer id){



      return   paymentService.PaymentCircuitBreaker(id);
    }





}
