package com.sxl.springcloud.service;


import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackService implements PaymentHystrixService{
    @Override
    public String payment_info(Integer id) {
        return "==============null";
    }

    @Override
    public String payment_info1(Integer id) {
        return "=========***************====null";
    }
}
