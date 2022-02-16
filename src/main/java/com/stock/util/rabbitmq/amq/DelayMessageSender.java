package com.stock.util.rabbitmq.amq;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.stock.util.rabbitmq.amq.DirectRabbitConfig.*;
import static com.stock.util.rabbitmq.config.DelayQueueConfig.*;

@Component
public class DelayMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 设置队列延时
     * @param msg
     * @param type
     */
    public void sendMsg(String msg, String type) {
        switch (type) {
            case "DELAY_10s":
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEA_ROUTING_KEY, msg);
                break;
            case "DELAY_60s":
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEB_ROUTING_KEY, msg);
                break;
            case "DELAY":
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEC_ROUTING_KEY, msg);
        }
    }

    /**
     * 设置消息体延时
     * @param messagePostProcessor
     * @param msg
     */
    public void sendMsg(MessagePostProcessor messagePostProcessor, String msg) {
        rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEC_ROUTING_KEY, msg, messagePostProcessor);
    }

    /**
     * 发送延时消息(依赖延时插件)
     * @param msg
     * @param delayTime
     */
    public void sendDelayMsg(String msg, Integer delayTime) {
        rabbitTemplate.convertAndSend(DELAYED_EXCHANGE_NAME, DELAYED_ROUTING_KEY, msg, a ->{
            a.getMessageProperties().setDelay(delayTime);
            return a;
        });
    }
}
