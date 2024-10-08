package com.heal.ms.infrastructure.rabbit;

import com.heal.ms.domain.aggregates.HttpReportAggregate;
import com.heal.ms.domain.services.ReportProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-08 12:10:11
 */
@Service
public class HttpReportProducerService implements ReportProducerService<HttpReportAggregate> {

    @Value("${http.rabbit.queue.name}")
    private String httpQueueName;

    private final RabbitTemplate rabbitTemplate;

    public HttpReportProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void produce(HttpReportAggregate message) {
        rabbitTemplate.convertAndSend(httpQueueName, message);
    }
}
