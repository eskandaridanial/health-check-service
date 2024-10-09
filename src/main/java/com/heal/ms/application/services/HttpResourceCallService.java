package com.heal.ms.application.services;

import com.heal.ms.domain.aggregates.HttpReportAggregate;
import com.heal.ms.domain.entities.HttpResource;
import com.heal.ms.domain.services.ReportProducerService;
import com.heal.ms.domain.services.ResourceCallService;
import com.heal.ms.utility.HttpUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 19:22:04
 */
@Component
public class HttpResourceCallService implements ResourceCallService<HttpResource> {

    private final HttpUtils httpUtils;
    private final ReportProducerService<HttpReportAggregate> reportProducerService;

    public HttpResourceCallService(HttpUtils httpUtils, ReportProducerService<HttpReportAggregate> reportProducerService) {
        this.httpUtils = httpUtils;
        this.reportProducerService = reportProducerService;
    }

    @Override
    public void call(HttpResource httpResource) {
        long startTime = System.currentTimeMillis();
        Boolean isHealthy = false;
        try {
            isHealthy = httpUtils.is2xxSuccessful(httpResource);
        } finally {
            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;

            Boolean finalIsHealthy = isHealthy;
            CompletableFuture.runAsync(() -> reportProducerService.produce(
                    new HttpReportAggregate(
                            httpResource,
                            finalIsHealthy,
                            responseTime)));
        }

    }
}
