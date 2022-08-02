package com.sxl.springcloud.controller;


import com.sxl.springcloud.entities.CommonResult;
import com.sxl.springcloud.entities.Payment;
import com.sxl.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService  paymentFeignService;


    @GetMapping("/consumer/feign/get/{id}")
    public CommonResult<Payment>  byId(@PathVariable("id") Long id){
         return paymentFeignService.byId(id);
    }

    @GetMapping("/payment/feign/timeout")
    public String  paymentFeignTimeout(){

        return paymentFeignService.paymentFeignTimeout();
    }
}
