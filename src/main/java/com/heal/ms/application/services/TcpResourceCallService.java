package com.heal.ms.application.services;

import com.heal.ms.domain.aggregates.TcpReportAggregate;
import com.heal.ms.domain.entities.TcpResource;
import com.heal.ms.domain.services.ReportProducerService;
import com.heal.ms.domain.services.ResourceCallService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 19:22:04
 */
@Component
public class TcpResourceCallService implements ResourceCallService<TcpResource> {

    private final ReportProducerService<TcpReportAggregate> reportProducerService;

    public TcpResourceCallService(ReportProducerService<TcpReportAggregate> reportProducerService) {
        this.reportProducerService = reportProducerService;
    }

    @Override
    public void call(TcpResource tcpResource) {
        try {
            long startTime = System.currentTimeMillis();
            boolean isHealthy = false;
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(tcpResource.getIp().getIp(), tcpResource.getPort().getPort()), tcpResource.getTimeout());
                isHealthy = socket.isConnected();
            } finally {
                long endTime = System.currentTimeMillis();
                long responseTime = endTime - startTime;
                boolean finalIsHealthy = isHealthy;

                CompletableFuture.runAsync(() -> reportProducerService.produce(
                        new TcpReportAggregate(
                                tcpResource,
                                finalIsHealthy,
                                responseTime)));
            }
        } catch (IOException ex) {
            // todo: based on the business needs we can persist failures on a fallback storage
        }
    }
}
