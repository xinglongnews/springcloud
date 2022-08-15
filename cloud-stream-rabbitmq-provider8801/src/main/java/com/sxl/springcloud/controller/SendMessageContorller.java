package com.sxl.springcloud.controller;

import com.sxl.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageContorller {


    @Resource
    private IMessageProvider iMessageProvider;

    @GetMapping("/getMessage")
    public String  sendMessage(){
        return iMessageProvider.sendMessage();
    }




}
