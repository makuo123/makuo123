package com.stock.util.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DirecterReceiver {

    @RabbitListener(queues = "directQueue")
    @RabbitHandler
    public void process(Map message){
        System.out.println("DirectReceive接收到的消息 ：" + message.toString());
    }

    @RabbitListener(queues = "fanoutQueue")
    @RabbitHandler
    public void process1(Map message){
        System.out.println("fanoutQueue接收到的消息 ：" + message.toString());
    }

    @RabbitListener(queues = "topic.q1")
    @RabbitHandler
    public void process4(Map message){
        System.out.println("topic.q1接收到的消息 ：" + message.toString());
    }

    @RabbitListener(queues = "topic.q2")
    @RabbitHandler
    public void process2(Map message){
        System.out.println("topic.q2接收到的消息 ：" + message.toString());
    }

    @RabbitListener(queues = "topic.q3")
    @RabbitHandler
    public void process3(Map message){
        System.out.println("topic.q3接收到的消息 ：" + message.toString());
    }
}
