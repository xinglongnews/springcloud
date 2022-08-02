package com.sxl.springcloud.service;


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id){
//        try {
//            TimeUnit.SECONDS.sleep(5);
////            Thread.sleep(2000);
//        }catch (Exception e){
//
//            e.getStackTrace();
//        }
        return "线程池："+Thread.currentThread().getName()+"  payment_ok id="+id;
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandle",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")
    })
    public String paymentInfo_ok1(Integer id){
        System.err.println(Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
//            Thread.sleep(2000);
        }catch (Exception e){

            e.getStackTrace();
        }

        return "线程池："+"  payment_timeout id="+id;
    }


    public String  paymentInfo_TimeoutHandle(Integer id){
        System.out.println(Thread.currentThread().getName());
        return "超时";



    }


    //服务熔断

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//失败率达到多少跳闸

    })
    public String  PaymentCircuitBreaker(@PathVariable("id")Integer id){

        if(id<0){

            throw new RuntimeException();

        }
        String s = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功流水号"+s;

    }

    public String  paymentCircuitBreaker_fallback(@PathVariable("id")Integer id){


        return "id不能是负数";
    }

}
