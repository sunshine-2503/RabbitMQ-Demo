package com.shaoming.comm.config.rabbit.fanout;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列方式：Fanout
 * Fanout 转发消息到所有绑定队列。
 */
@Configuration
public class FanoutTopicConfig {

    // 交换器
    private static final String FANOUT_EXCHANGE = "FanoutExchange";

    /**
     * 声明 交互器
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

}
