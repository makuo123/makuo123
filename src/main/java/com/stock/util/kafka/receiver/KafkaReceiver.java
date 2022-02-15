package com.stock.util.kafka.receiver;

import com.stock.util.kafka.channel.Sink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @Author mk
 * @Date 2020/11/26 16:19
 * @Version 1.0
 */
//@EnableBinding(Sink.class)
public class KafkaReceiver {

    private final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    @StreamListener(Sink.INPUT_1)
    private void receive(String vote) {
        logger.info("receive message : " + vote);
    }

}
