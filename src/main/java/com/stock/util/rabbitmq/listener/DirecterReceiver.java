package com.stock.util.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "directQueue")
public class DirecterReceiver {

    @RabbitHandler
    public void process(Map message){
        System.out.println("DirectReceive接收到的消息 ：" + message.toString());
    }
}
