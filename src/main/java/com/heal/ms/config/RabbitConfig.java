package com.heal.ms.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-08 12:05:48
 */
@Configuration
public class RabbitConfig {

    @Value("${tcp.rabbit.queue.name}")
    private String tcpQueueName;

    @Value("${http.rabbit.queue.name}")
    private String httpQueueName;

    @Bean
    public Queue tcpQueue() {
        return new Queue(tcpQueueName, false);
    }

    @Bean
    public Queue httpQueue() {
        return new Queue(httpQueueName, false);
    }
}
