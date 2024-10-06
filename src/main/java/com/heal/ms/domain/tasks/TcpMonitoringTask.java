package com.heal.ms.domain.tasks;

import com.heal.ms.application.services.TcpResourceCallService;
import com.heal.ms.domain.entities.TcpResource;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 19:20:01
 */
@Slf4j
public class TcpMonitoringTask implements Runnable {

    private final TcpResourceCallService tcpResourceCallService;
    private final ScheduledExecutorService scheduler;
    private final TcpResource tcpResource;
    private volatile boolean cancelled = false;

    public TcpMonitoringTask(TcpResource tcpResource, ScheduledExecutorService scheduler, TcpResourceCallService tcpResourceCallService) {
        this.tcpResource = tcpResource;
        this.scheduler = scheduler;
        this.tcpResourceCallService = tcpResourceCallService;
    }

    public void cancel() {
        cancelled = true;
    }

    @Override
    public void run() {
        if (cancelled) return;

        try {
            tcpResourceCallService.call(tcpResource);
        } finally {
            if (!cancelled) {
                long intervalInMs = tcpResource.getIntervalInMs();
                scheduler.schedule(this, intervalInMs, TimeUnit.MILLISECONDS);
            }
        }
    }
}
