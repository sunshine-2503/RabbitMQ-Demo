package com.shaoming.comm.config.rabbit.direct;

import org.springframework.context.annotation.Configuration;

/**
 * 消息队列方式：Direct
 * direct 类型的行为是"先匹配, 再投送". 即在绑定时设定一个 routing_key, 消息的routing_key完全匹配时, 才会被交换器投送到绑定的队列中去。
 */
@Configuration
public class RabbitDirectConfig {

    // 交换器
    private static final String DIRECT_EXCHANGE = "DirectExchange";


}
