package com.heal.ms.infrastructure.tasks;

import com.heal.ms.domain.entities.TcpResource;
import com.heal.ms.domain.services.ResourceCallService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 19:20:01
 */
@Slf4j
public class TcpMonitoringTask implements Runnable {

    private final ResourceCallService<TcpResource> resourceCallService;
    private final ScheduledExecutorService scheduler;
    private final TcpResource tcpResource;
    private volatile boolean cancelled = false;

    public TcpMonitoringTask(TcpResource tcpResource, ScheduledExecutorService scheduler, ResourceCallService<TcpResource> resourceCallService) {
        this.tcpResource = tcpResource;
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
            resourceCallService.call(tcpResource);
        } finally {
            if (!cancelled) {
                long intervalInMs = tcpResource.getIntervalInMs();
                scheduler.schedule(this, intervalInMs, TimeUnit.MILLISECONDS);
            }
        }
    }
}
