package com.shaoming.comm.config.rabbit.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_EXCHANGE, RabbitTopicConfig.TOPIC_BINDING_1, msg);
    }

}
