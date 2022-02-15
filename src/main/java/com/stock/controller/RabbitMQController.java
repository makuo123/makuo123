package com.stock.controller;

import com.alibaba.fastjson.JSONObject;
import com.stock.entity.R;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/send/{msg}")
    public void sendMsg(@PathVariable("msg") String msg){
        JSONObject object = new JSONObject();
        object.put("msg", msg);
        rabbitTemplate.convertAndSend("directerExchange","testDirectQueue", object);
    }

    @RequestMapping("/test")
    public R test(){
        int a = 1/0;
        return new R();
    }
}
