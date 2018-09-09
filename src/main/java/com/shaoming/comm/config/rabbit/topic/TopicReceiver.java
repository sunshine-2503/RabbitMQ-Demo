package com.shaoming.comm.config.rabbit.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {

    @RabbitListener(queues = RabbitTopicConfig.TOPIC_QUEUE_1)
    public void processMessage1(String msg) {
        System.out.println("接收1 ==== "+msg);
    }

    @RabbitListener(queues = RabbitTopicConfig.TOPIC_QUEUE_2)
    public void processMessage2(String msg) {
        System.out.println("接收2 ==== "+msg);
    }
}
