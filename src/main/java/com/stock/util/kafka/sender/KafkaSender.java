package com.stock.util.kafka.sender;

import com.stock.util.kafka.channel.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @Author mk
 * @Date 2020/11/26 16:13
 * @Version 1.0
 */
//@EnableBinding(Source.class)
public class KafkaSender {

    private final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private Source source;

    public void sendMessage(String message) {
        try {
            source.output1().send(MessageBuilder.withPayload("message: " + message).build());
        } catch (Exception e) {
            logger.info("消息发送失败，原因："+e);
            e.printStackTrace();
        }
    }
}
