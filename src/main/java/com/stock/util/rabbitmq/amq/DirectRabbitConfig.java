package com.stock.util.rabbitmq.amq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DirectRabbitConfig {

    public static final String DELAY_EXCHANGE_NAME = "delay.queue.demo.business.exchange";
    public static final String DELAY_QUEUEA_NAME = "delay.queue.demo.business.queuea";
    public static final String DELAY_QUEUEB_NAME = "delay.queue.demo.business.queueb";
    public static final String DELAY_QUEUEC_NAME = "delay.queue.demo.business.queuec";
    public static final String DELAY_QUEUEA_ROUTING_KEY = "delay.queue.demo.business.queuea.routingkey";
    public static final String DELAY_QUEUEB_ROUTING_KEY = "delay.queue.demo.business.queueb.routingkey";
    public static final String DELAY_QUEUEC_ROUTING_KEY = "delay.queue.demo.business.queuec.routingkey";
    public static final String DEAD_LETTER_EXCHANGE = "delay.queue.demo.deadletter.exchange";
    public static final String DEAD_LETTER_QUEUEA_ROUTING_KEY = "delay.queue.demo.deadletter.delay_10s.routingkey";
    public static final String DEAD_LETTER_QUEUEB_ROUTING_KEY = "delay.queue.demo.deadletter.delay_60s.routingkey";
    public static final String DEAD_LETTER_QUEUEC_ROUTING_KEY = "delay.queue.demo.deadletter.delay_.routingkey";
    public static final String DEAD_LETTER_QUEUEA_NAME = "delay.queue.demo.deadletter.queuea";
    public static final String DEAD_LETTER_QUEUEB_NAME = "delay.queue.demo.deadletter.queueb";
    public static final String DEAD_LETTER_QUEUEC_NAME = "delay.queue.demo.deadletter.queuec";

    // 队列
    @Bean
    public Queue registerDirectQueue(){
        return new Queue("directQueue", true);
    }

    @Bean
    public Queue registerFanoutQueue(){
        return new Queue("fanoutQueue", true);
    }

    @Bean
    public Queue registerTopicQueue(){
        return new Queue("topic.q1", true);
    }
    @Bean
    public Queue registerTopicQueue2(){
        return new Queue("topic.q2", true);
    }
    @Bean
    public Queue registerTopicQueue3(){
        return new Queue("topic.q3", true);
    }

    // 交换机(点对点模式)
    @Bean
    public DirectExchange registerDirectExchange(){
        return new DirectExchange("directerExchange");
    }

    // 绑定 将队列与交换机绑定，并设置用于匹配的键
    @Bean
    Binding binding(){
        return BindingBuilder.bind(registerDirectQueue()).to(registerDirectExchange()).with("testDirectQueue");
    }

    // 交换机（广播模式）
    @Bean
    public FanoutExchange registerFanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding binding1(){
        return BindingBuilder.bind(registerDirectQueue()).to(registerFanoutExchange());
    }

    @Bean
    Binding binding2(){
        return BindingBuilder.bind(registerFanoutQueue()).to(registerFanoutExchange());
    }

    // 交换机（topic）
    @Bean
    public TopicExchange registerTopicExchange(){
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding bindingTopic(){
        return BindingBuilder.bind(registerTopicQueue()).to(registerTopicExchange()).with("topic.q1");
    }

    /**
     * 符号“#”匹配路由键的一个或多个词，符号“*”匹配路由键的一个词。
     *
     * 例如：
     *
     * topic.#那么这个队列会会接收topic开头的消息
     *
     * topic.*.queue那么这个队列会接收topic.aaaa.queue这样格式的消息，不接收能topic.aaaa.bbbb.queue这样格式的消息
     * @return
     */
    @Bean
    public Binding bindingTopic2(){
        return BindingBuilder.bind(registerTopicQueue2()).to(registerTopicExchange()).with("topic.#");
    }

    @Bean
    public Binding bindingTopic3(){
        return BindingBuilder.bind(registerTopicQueue3()).to(registerTopicExchange()).with("topic.*");
    }

    // 延时交换机
    @Bean("delayExchange")
    public DirectExchange delayDirectExchange(){
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    // 死信交换机
    @Bean("deadLetterExchange")
    public DirectExchange deadDirectExchange(){
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    // 延时队列
    @Bean("delayQueueA")
    public Queue delayQueueA(){
        Map<String , Object> args = new HashMap<>();
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEA_ROUTING_KEY);
        // x-message-ttl  声明队列的TTL
        args.put("x-message-ttl", 10000);
        return QueueBuilder.durable(DELAY_QUEUEA_NAME).withArguments(args).build();
    }

    // 延时队列
    @Bean("delayQueueB")
    public Queue delayQueueB(){
        Map<String , Object> args = new HashMap<>();
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEB_ROUTING_KEY);
        // x-message-ttl  声明队列的TTL
        args.put("x-message-ttl", 60000);
        return QueueBuilder.durable(DELAY_QUEUEB_NAME).withArguments(args).build();
    }

    // 延时队列绑定延时交换机
    @Bean
    public Binding delayBindingA(@Qualifier("delayQueueA") Queue queue,
                                @Qualifier("delayExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUEA_ROUTING_KEY);
    }
    // 延时队列绑定延时交换机
    @Bean
    public Binding delayBindingB(@Qualifier("delayQueueB") Queue queue,
                                @Qualifier("delayExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUEB_ROUTING_KEY);
    }

    // 死信队列
    @Bean("deadLetterQueueA")
    public Queue deadQueueA(){
        return new Queue(DEAD_LETTER_QUEUEA_NAME);
    }
    @Bean("deadLetterQueueB")
    public Queue deadQueueB(){
        return new Queue(DEAD_LETTER_QUEUEB_NAME);
    }

    // 绑定死信队列和死信交换机
    @Bean
    public Binding deadABinding(@Qualifier("deadLetterQueueA") Queue queue,
                                @Qualifier("deadLetterExchange")DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUEA_ROUTING_KEY);
    }
    @Bean
    public Binding deadBBinding(@Qualifier("deadLetterQueueB") Queue queue,
                                @Qualifier("deadLetterExchange")DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUEB_ROUTING_KEY);
    }

    @Bean("delayQueueC")
    public Queue delayQueueC(){
        Map<String , Object> args = new HashMap<>();
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEC_ROUTING_KEY);
        return QueueBuilder.durable(DELAY_QUEUEC_NAME).withArguments(args).build();
    }
    @Bean
    public Binding delayBindingC(@Qualifier("delayQueueC") Queue queue,
                                 @Qualifier("delayExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUEC_ROUTING_KEY);
    }

    @Bean("deadLetterQueueC")
    public Queue deadQueueC(){
        return new Queue(DEAD_LETTER_QUEUEC_NAME);
    }

    @Bean
    public Binding deadCBinding(@Qualifier("deadLetterQueueC") Queue queue,
                                @Qualifier("deadLetterExchange")DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUEC_ROUTING_KEY);
    }
}
