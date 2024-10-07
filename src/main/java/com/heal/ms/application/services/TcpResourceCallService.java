package com.heal.ms.application.services;

import com.heal.ms.domain.entities.TcpResource;
import com.heal.ms.domain.services.ResourceCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 19:22:04
 */
@Slf4j
@Component
public class TcpResourceCallService implements ResourceCallService<TcpResource> {

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
                log.info("TCP health check for {}:{} - Healthy: {}, Response time: {} ms",
                        tcpResource.getIp().getIp(), tcpResource.getPort().getPort(), isHealthy, responseTime);
            }
        } catch (IOException ex) {
            // todo: based on the business needs we can persist failures on a fallback storage
        }
    }
}
