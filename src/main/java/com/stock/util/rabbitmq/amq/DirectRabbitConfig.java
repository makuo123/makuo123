package com.stock.util.rabbitmq.amq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    // 队列
    @Bean
    public Queue registerDirectQueue(){
        return new Queue("directQueue", true);
    }

    // 交换机
    @Bean
    public DirectExchange registerDirectExchange(){
        return new DirectExchange("directerExchange");
    }

    // 绑定 将队列与交换机绑定，并设置用于匹配的键
    @Bean
    Binding binding(){
        return BindingBuilder.bind(registerDirectQueue()).to(registerDirectExchange()).with("testDirectQueue");
    }
}
