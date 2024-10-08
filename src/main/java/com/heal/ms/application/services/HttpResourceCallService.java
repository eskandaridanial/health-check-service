package com.heal.ms.application.services;

import com.heal.ms.domain.entities.HttpResource;
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
public class HttpResourceCallService implements ResourceCallService<HttpResource> {

    @Override
    public void call(HttpResource httpResource) {
        // todo: implement a dynamic restfull call based on the http method
        log.info("Sending Http Request...");
    }
}
