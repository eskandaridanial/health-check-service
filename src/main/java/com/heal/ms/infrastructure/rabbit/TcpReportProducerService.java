package com.heal.ms.infrastructure.rabbit;

import com.heal.ms.domain.aggregates.TcpReportAggregate;
import com.heal.ms.domain.services.ReportProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-08 12:10:11
 */
@Service
public class TcpReportProducerService implements ReportProducerService<TcpReportAggregate> {

    @Value("${tcp.rabbit.queue.name}")
    private String tcpQueueName;

    private final RabbitTemplate rabbitTemplate;

    public TcpReportProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void produce(TcpReportAggregate message) {
        rabbitTemplate.convertAndSend(tcpQueueName, message);
    }
}
