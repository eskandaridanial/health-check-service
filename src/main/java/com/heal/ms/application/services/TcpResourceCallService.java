package com.heal.ms.application.services;

import com.heal.ms.domain.entities.TcpResource;
import com.heal.ms.domain.services.ResourceCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 19:22:04
 */
@Slf4j
@Component
public class TcpResourceCallService implements ResourceCallService<TcpResource> {

    @Override
    public void call(TcpResource tcpResource) {
        log.info("Establishing TCP Connection to " + tcpResource.getIp().getIp() + ":" + tcpResource.getPort().getPort());
    }
}
