package com.heal.ms.infrastructure.rabbit;

import com.heal.ms.domain.aggregates.TcpReportAggregate;
import com.heal.ms.domain.services.NotificationService;
import com.heal.ms.domain.services.ReportConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-08 12:10:11
 */
@Slf4j
@Service
public class TcpConsoleReportConsumerService implements ReportConsumerService<TcpReportAggregate> {

    private final NotificationService<TcpReportAggregate> notificationService;

    public TcpConsoleReportConsumerService(NotificationService<TcpReportAggregate> notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    @RabbitListener(queues = "${tcp.rabbit.queue.name}")
    public void consume(TcpReportAggregate tcpReportAggregate) {
        notificationService.sendNotification(tcpReportAggregate);
    }
}
