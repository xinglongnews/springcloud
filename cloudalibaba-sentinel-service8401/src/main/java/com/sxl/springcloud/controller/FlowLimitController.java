package com.sxl.springcloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {


    @GetMapping("/testA")
    public String  testA() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(800);
        return "--------testA";
    }


    @GetMapping("/testB")
    public String  testB(){

        log.info(Thread.currentThread().getName()+"*******stestB");
        return "-------testB";
    }


    @GetMapping("/getHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String  hotKey(@RequestParam(value = "p1",required = false)String  p1,
                          @RequestParam(value = "P2",required = false)String  p2){

        return "-----------hotkey";

    }

    public String  deal_testHotKey(String  p1,String  p2,BlockException e){
        return "-----------hotkey哭";
    }
}
