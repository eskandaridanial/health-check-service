package com.heal.ms.infrastructure.tasks;

import com.heal.ms.domain.entities.HttpResource;
import com.heal.ms.domain.services.ResourceCallService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 19:20:01
 */
@Slf4j
public class HttpMonitoringTask implements Runnable {

    private final ResourceCallService<HttpResource> resourceCallService;
    private final ScheduledExecutorService scheduler;
    private final HttpResource httpResource;
    private volatile boolean cancelled = false;

    public HttpMonitoringTask(HttpResource httpResource, ScheduledExecutorService scheduler, ResourceCallService<HttpResource> resourceCallService) {
        this.httpResource = httpResource;
        this.scheduler = scheduler;
        this.resourceCallService = resourceCallService;
    }

    public void cancel() {
        cancelled = true;
    }

    @Override
    public void run() {
        if (cancelled) return;

        try {
            resourceCallService.call(httpResource);
        } finally {
            if (!cancelled) {
                long intervalInMs = httpResource.getIntervalInMs();
                scheduler.schedule(this, intervalInMs, TimeUnit.MILLISECONDS);
            }
        }
    }
}
