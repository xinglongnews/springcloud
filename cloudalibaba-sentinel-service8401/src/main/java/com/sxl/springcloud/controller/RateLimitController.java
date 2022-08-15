package com.sxl.springcloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sxl.springcloud.entities.CommonResult;
import com.sxl.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {


    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){




        return new CommonResult(200,"按资源名成称限流测试ok",new Payment(200L,"serial001"));
    }

    public CommonResult handleException(BlockException blockException){

        return new CommonResult(444,blockException.getClass().getCanonicalName()+"服务可以用 ");



    }
}
