# SpringBoot - RabbitMQ 案例

### Spring Boot 集成 RabbitMQ步骤如下：

1、安装 RabbitMQ

```text
参考地址：https://blog.csdn.net/newbie_907486852/article/details/79788471
```

2、新建spring-boot项目，引入 rabbit-mq 依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

3、添加配置到 application.yml 文件

```yaml
server:
  port: 5566

spring:
  # 配置 RabbitMQ - Spring
  rabbitmq:
    host: 127.0.0.1
    port: 5672
    username: guest
    password: guest
    virtual-host: /
    publisher-confirms: true
```

4、创建 RabbitMQ 配置文件 : RabbitTopicConfig.java 内容如下：

```java
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
```

5、创建 生产者 ： TopicSender.java 内容如下：

```java
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
```

6、创建 消费者 ： TopicReceiver.java 内容如下：

```java
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
```

7、创建发送消息接口

```java
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
```

8、启动项目，调用发送消息接口：http://localhost:5566/message/send 调用成功后你会看到如下内容：

```text
生产者生产消息 ======== 消息内容如下：  第0条消息：哈哈哈哈！
生产者生产消息 ======== 消息内容如下：  第1条消息：哈哈哈哈！
生产者生产消息 ======== 消息内容如下：  第2条消息：哈哈哈哈！
生产者生产消息 ======== 消息内容如下：  第3条消息：哈哈哈哈！
生产者生产消息 ======== 消息内容如下：  第4条消息：哈哈哈哈！
生产者生产消息 ======== 消息内容如下：  第5条消息：哈哈哈哈！
生产者生产消息 ======== 消息内容如下：  第6条消息：哈哈哈哈！
生产者生产消息 ======== 消息内容如下：  第7条消息：哈哈哈哈！
生产者生产消息 ======== 消息内容如下：  第8条消息：哈哈哈哈！
生产者生产消息 ======== 消息内容如下：  第9条消息：哈哈哈哈！
=======================================================
消费者 1 ======== 消费消息：  第0条消息：哈哈哈哈！
消费者 2 ======== 消费消息：  第0条消息：哈哈哈哈！
消费者 2 ======== 消费消息：  第1条消息：哈哈哈哈！
消费者 1 ======== 消费消息：  第1条消息：哈哈哈哈！
消费者 2 ======== 消费消息：  第2条消息：哈哈哈哈！
消费者 1 ======== 消费消息：  第2条消息：哈哈哈哈！
消费者 2 ======== 消费消息：  第3条消息：哈哈哈哈！
消费者 2 ======== 消费消息：  第4条消息：哈哈哈哈！
消费者 1 ======== 消费消息：  第3条消息：哈哈哈哈！
消费者 2 ======== 消费消息：  第5条消息：哈哈哈哈！
消费者 1 ======== 消费消息：  第4条消息：哈哈哈哈！
消费者 2 ======== 消费消息：  第6条消息：哈哈哈哈！
消费者 1 ======== 消费消息：  第5条消息：哈哈哈哈！
消费者 2 ======== 消费消息：  第7条消息：哈哈哈哈！
消费者 1 ======== 消费消息：  第6条消息：哈哈哈哈！
消费者 1 ======== 消费消息：  第7条消息：哈哈哈哈！
消费者 2 ======== 消费消息：  第8条消息：哈哈哈哈！
消费者 1 ======== 消费消息：  第8条消息：哈哈哈哈！
消费者 2 ======== 消费消息：  第9条消息：哈哈哈哈！
消费者 1 ======== 消费消息：  第9条消息：哈哈哈哈！
```


