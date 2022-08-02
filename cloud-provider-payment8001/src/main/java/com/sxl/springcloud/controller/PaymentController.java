package com.sxl.springcloud.controller;


import com.sxl.springcloud.entities.CommonResult;
import com.sxl.springcloud.entities.Payment;
import com.sxl.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {


    @Value("${server.port}")
    private String port;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult  create(@RequestBody Payment payment){
       log.info("======="+port+"==========="+payment);

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

        System.out.println("******************"+port+"*********");


        return new CommonResult(200,"查询成功"+port,paymentById);


    }

    @GetMapping("/payment/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        for (String service : services) {

            System.out.println(service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId()+"  **************     "+instance.getHost());
        }

        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String  getPort(){

        return port;


    }

    @GetMapping("/payment/feign/timeout")
    public String  paymentFeignTimeout(){

        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        return port;
    }
}
