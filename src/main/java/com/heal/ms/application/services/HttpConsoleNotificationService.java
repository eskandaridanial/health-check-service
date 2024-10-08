package com.heal.ms.application.services;

import com.heal.ms.domain.aggregates.HttpReportAggregate;
import com.heal.ms.domain.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-08 13:49:53
 */
@Slf4j
@Component
public class HttpConsoleNotificationService implements NotificationService<HttpReportAggregate> {

    @Override
    public void sendNotification(HttpReportAggregate tcpResource) {
        log.info("Http health check for {} with address {} - Healthy: {}, Response time: {} ms",
                tcpResource.getResource().getName(),
                tcpResource.getResource().getUrl(),
                tcpResource.getIsHealthy(),
                tcpResource.getLatency());
    }
}
