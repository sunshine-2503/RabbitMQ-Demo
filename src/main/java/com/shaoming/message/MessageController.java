package com.shaoming.message;

import com.shaoming.comm.config.rabbit.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private TopicSender topicSender;


    @GetMapping("/send")
    public String send1() {
        for (int i = 0; i < 10; i++) {
            topicSender.send("第"+i+"条消息：哈哈哈哈！");
        }
        System.out.println("=======================================================");
        return "success";
    }

}
