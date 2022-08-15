package com.sxl.springcloud.iml;

import com.sxl.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;


@EnableBinding(Source.class)
public class MessageProviderIml implements IMessageProvider {


    @Resource
    private MessageChannel output;//消息发送管道




    @Override
    public String sendMessage() {

        String  messge= UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(messge).build());
        return messge;
    }
}
