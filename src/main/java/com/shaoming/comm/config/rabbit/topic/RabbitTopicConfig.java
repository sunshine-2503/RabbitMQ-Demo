package com.shaoming.comm.config.rabbit.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列方式：Topic
 * Topic 按规则转发消息（最灵活）。
 */
@Configuration
public class RabbitTopicConfig {

    // 交换机名称
    public static final String TOPIC_EXCHANGE = "TopicExchange";

    // 队列一名称
    public final static String TOPIC_QUEUE_1 = "TOPIC.QUEUE_1";

    // 队列二名称
    public final static String TOPIC_QUEUE_2 = "TOPIC.QUEUE_2";

    // 绑定的值一
    public final static String TOPIC_BINDING_1 = "TOPIC.BINDING_1";

    // 绑定的值二
    public final static String TOPIC_BINDING_2 = "TOPIC.BINDING_2";

    /**
     * 声明 交换机
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    /**
     * 定义 队列一
     */
    @Bean
    Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE_1, true);
    }

    /**
     * 定义 队列二
     */
    @Bean
    Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE_2, true);
    }

    /**
     * 定义 绑定
     */
    @Bean
    Binding topicBinding1(Queue topicQueue1, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with(TOPIC_BINDING_1);
    }

    /**
     * 定义 绑定
     */
    @Bean
    Binding topicBinding2(Queue topicQueue2, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with(TOPIC_BINDING_1);
    }
}
