package com.heal.ms.infrastructure.rabbit;

import com.heal.ms.domain.aggregates.HttpReportAggregate;
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
public class HttpConsoleReportConsumerService implements ReportConsumerService<HttpReportAggregate> {

    private final NotificationService<HttpReportAggregate> notificationService;

    public HttpConsoleReportConsumerService(NotificationService<HttpReportAggregate> notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    @RabbitListener(queues = "${http.rabbit.queue.name}")
    public void consume(HttpReportAggregate httpReportAggregate) {
        notificationService.sendNotification(httpReportAggregate);
    }
}
