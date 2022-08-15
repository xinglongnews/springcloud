package com.sxl.springcloud.service;


import com.sxl.springcloud.entities.CommonResult;
import com.sxl.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/byId/{id}")
    CommonResult<Payment> byId(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/timeout")
    public String  paymentFeignTimeout();
}
