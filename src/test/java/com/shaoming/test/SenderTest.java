package com.shaoming.test;

import com.shaoming.comm.config.rabbit.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SenderTest {

    @Autowired
    private TopicSender topicSender;

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            String msg = "第"+i+"条消息：哈哈哈哈！";
            System.out.println("发送 ====== 第"+i+"条消息：哈哈哈哈！");
            topicSender.send(msg);
        }
    }

}
