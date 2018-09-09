package com.shaoming.comm.config.rabbit.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {

    @RabbitListener(queues = RabbitTopicConfig.TOPIC_QUEUE_1)
    public void processMessage1(String msg) {
        System.out.println("消费者 1 ======== 消费消息：  "+msg);
    }

    @RabbitListener(queues = RabbitTopicConfig.TOPIC_QUEUE_2)
    public void processMessage2(String msg) {
        System.out.println("消费者 2 ======== 消费消息：  "+msg);
    }
}
