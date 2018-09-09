package com.shaoming.comm.config.rabbit.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        System.out.println("生产者生产消息 ======== 消息内容如下：  "+msg);
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_EXCHANGE, RabbitTopicConfig.TOPIC_BINDING_1, msg);
    }

}
