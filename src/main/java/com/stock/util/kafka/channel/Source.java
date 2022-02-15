package com.stock.util.kafka.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Author mk
 * @Date 2020/11/26 16:10
 * @Version 1.0
 */
public interface Source {

    //发送队列1
    String OUTPUT_1 = "sourceA";

    @Output(Source.OUTPUT_1)
    MessageChannel output1();

}
