package com.heal.ms.domain.tasks;

import com.heal.ms.application.services.TcpResourceCallService;
import com.heal.ms.domain.entities.TcpResource;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 19:20:01
 */
public class TcpMonitoringTask implements Runnable {

    private final TcpResourceCallService tcpResourceCallService;
    private final ScheduledExecutorService scheduler;
    private final TcpResource tcpResource;

    public TcpMonitoringTask(TcpResource tcpResource, ScheduledExecutorService scheduler, TcpResourceCallService tcpResourceCallService) {
        this.tcpResource = tcpResource;
        this.scheduler = scheduler;
        this.tcpResourceCallService = tcpResourceCallService;
    }

    @Override
    public void run() {
        try {
            tcpResourceCallService.call(tcpResource);
        } finally {
            long intervalInMs = tcpResource.getIntervalInMs();
            scheduler.schedule(this, intervalInMs, TimeUnit.MILLISECONDS);
        }
    }
}
