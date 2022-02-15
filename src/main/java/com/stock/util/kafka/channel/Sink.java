package com.stock.util.kafka.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author mk
 * @Date 2020/11/26 16:08
 * @Version 1.0
 */
public interface Sink {

    String INPUT_1 = "testa";

    @Input(Sink.INPUT_1)
    SubscribableChannel input1();
}
