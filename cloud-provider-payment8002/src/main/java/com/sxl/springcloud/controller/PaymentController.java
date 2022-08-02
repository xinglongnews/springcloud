package com.sxl.springcloud.controller;


import com.sxl.springcloud.entities.CommonResult;
import com.sxl.springcloud.entities.Payment;
import com.sxl.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {


    @Value("${server.port}")
    private String  port;


    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult  create(@RequestBody Payment payment){
       log.info("======="+port+"=========="+payment);
       payment.setSerial("djfkdjfksd");

        int i = paymentService.create(payment);

        log.info("**插入**"+i);
        if(i>0){
            return new CommonResult(200,"插入成功"+port,i);

        }else {
            return new CommonResult(400,"插入失败"+port,i);
        }


    }

    @GetMapping("/payment/byId/{id}")
    public CommonResult<CommonResult>  byId(@PathVariable("id") Long id){

        Payment paymentById = paymentService.getPaymentById(id);

        System.out.println("******************"+port);
        return new CommonResult(200,"查询成功111"+port,paymentById);


    }
    @GetMapping("/payment/lb")
    public String  getPort(){




        return port;


    }
    @GetMapping("/payment/feign/timeout")
    public String  paymentFeignTimeout(){

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return port;
    }
}
