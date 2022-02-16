package com.stock.controller;

import com.alibaba.fastjson.JSONObject;
import com.stock.entity.R;
import com.stock.util.rabbitmq.amq.DelayMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
public class RabbitMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Resource
    private DelayMessageSender sender;

    @RequestMapping(value = "/send/{msg}")
    public void sendDirecterMsg(@PathVariable("msg") String msg) {
        JSONObject object = new JSONObject();
        object.put("msg", msg);
        rabbitTemplate.convertAndSend("directerExchange", "testDirectQueue", object);
    }

    @RequestMapping(value = "/send/fanout/{msg}")
    public String sendFanoutMsg1(@PathVariable("msg") String msg) {
        JSONObject object = new JSONObject();
        object.put("msg", msg);
        rabbitTemplate.convertAndSend("fanoutExchange", null, object);
        return "success";
    }

    @RequestMapping(value = "/send/topic/{msg}")
    public String sendTopicMsg(@PathVariable("msg") String msg) {
        /*JSONObject object = new JSONObject();
        object.put("msg", msg);*/
        Map object = new HashMap() {{
            put("msg", msg);
        }};
        rabbitTemplate.convertAndSend("topicExchange", "topic.q2", object);
        return "success";
    }

    @RequestMapping("/test")
    public R test() {
        int a = 1 / 0;
        return new R();
    }

    /**
     * 设置队列延时时间
     * @param msg
     * @param delayType
     */
    @RequestMapping("sendmsg")
    public void sendMsg(String msg, String delayType) {
        log.info("当前时间：{},收到请求，msg:{},delayType:{}", new Date(), msg, delayType);
        sender.sendMsg(msg, Objects.requireNonNull(delayType));
    }

    /**
     * 设置消息延迟时间
     * @param msg
     * @param time
     */
    @RequestMapping("sendmsg1")
    public void sendMsg1(String msg,String time) {
        log.info("当前时间：{},收到请求，msg:{}", new Date(),msg);
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(time);
                return message;
            }
        };

        sender.sendMsg(messagePostProcessor,msg);
    }


    @RequestMapping("sendmsg2")
    public void delayMsg2(String msg, Integer delayTime) {
        log.info("当前时间：{},收到请求，msg:{},delayTime:{}", new Date(), msg, delayTime);
        sender.sendDelayMsg(msg, delayTime);
    }
}
