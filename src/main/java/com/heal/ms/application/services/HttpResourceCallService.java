package com.heal.ms.application.services;

import com.heal.ms.domain.aggregates.HttpReportAggregate;
import com.heal.ms.domain.entities.HttpResource;
import com.heal.ms.domain.services.ReportProducerService;
import com.heal.ms.domain.services.ResourceCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 19:22:04
 */
@Slf4j
@Component
public class HttpResourceCallService implements ResourceCallService<HttpResource> {

    private final ReportProducerService<HttpReportAggregate> reportProducerService;

    public HttpResourceCallService(ReportProducerService<HttpReportAggregate> reportProducerService) {
        this.reportProducerService = reportProducerService;
    }

    @Override
    public void call(HttpResource httpResource) {
        // todo: implement a dynamic restfull call based on the http method
        log.info("Sending Http Request...");

        CompletableFuture.runAsync(() -> reportProducerService.produce(
                new HttpReportAggregate(
                        httpResource.getId().getId(),
                        true,
                        0L))); // todo: change these params later
    }
}
