package com.sxl.springcloud.controller;

import com.sxl.springcloud.entities.CommonResult;
import com.sxl.springcloud.entities.Payment;
import com.sxl.springcloud.lib.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

//    public static final String  PATH_URL="http://localhost:8001";
    public static final String  PATH_URL="http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;


    @Resource
    private DiscoveryClient discoveryClient;



    @PostMapping("/consumer/payment/creat")
    public CommonResult<Payment> create(Payment  payment){

        log.info("================"+payment);
        return  restTemplate.postForObject(PATH_URL + "/payment/create", payment, CommonResult.class);
    }


    @GetMapping("/consumer/payment/byId/{id}")
    public CommonResult<Payment>  getPayment(@PathVariable("id") Long id){
        log.error(" xiaing "+id);
        return restTemplate.getForObject(PATH_URL+"/payment/byId/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult<Payment>  getEntityPayment1(@PathVariable("id") Long id){
        log.error(" xiaing "+id);
//        return restTemplate.getForObject(PATH_URL+"/payment/byId/"+id,CommonResult.class);
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PATH_URL + "/payment/byId/" + id, CommonResult.class);

        if (forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        }else {

            return new CommonResult<>(404,"",null);
        }


    }


    @GetMapping("/consumer/payment/lb")
    public String  getPayment1(){

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances ==null || instances.size()<=0){

            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instance(instances);
        URI uri = serviceInstance.getUri();
        String forObject = restTemplate.getForObject(uri + "/payment/lb", String.class);
        return forObject;


//        log.error(" xiaing "+id);
//        return restTemplate.getForObject(PATH_URL+"/payment/byId/"+id,CommonResult.class);
    }


    @GetMapping("/consumer/payment/zipkip")
    public String  paymentZipkin(){
        String forObject = restTemplate.getForObject(PATH_URL + "/payment/zipkin", String.class);

        return  forObject;
    }


}
