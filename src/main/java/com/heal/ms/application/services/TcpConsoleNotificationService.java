package com.heal.ms.application.services;

import com.heal.ms.domain.aggregates.TcpReportAggregate;
import com.heal.ms.domain.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-08 13:49:53
 */
@Slf4j
@Component
public class TcpConsoleNotificationService implements NotificationService<TcpReportAggregate> {

    @Override
    public void sendNotification(TcpReportAggregate tcpResource) {
        log.info("TCP health check for {} with address {}:{} - Healthy: {}, Response time: {} ms",
                tcpResource.getResource().getName(),
                tcpResource.getResource().getIp().getIp(),
                tcpResource.getResource().getPort().getPort(),
                tcpResource.getIsHealthy(),
                tcpResource.getLatency());
    }
}
