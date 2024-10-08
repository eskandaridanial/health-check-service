package com.heal.ms.application.services;

import com.heal.ms.domain.entities.TcpResource;
import com.heal.ms.domain.services.MonitoringService;
import com.heal.ms.domain.services.ResourceCallService;
import com.heal.ms.domain.tasks.TcpMonitoringTask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 20:17:14
 */
@Component
public class TcpMonitoringService implements MonitoringService<TcpResource> {

    private final ResourceCallService<TcpResource> resourceCallService;
    private final ScheduledExecutorService scheduler;
    private final Map<String, TcpMonitoringTask> tasks = new ConcurrentHashMap<>();

    public TcpMonitoringService(@Value("${tcp.monitoring.thread-pool.size}") Integer threadPoolSize,
                                ResourceCallService<TcpResource> resourceCallService) {
        this.resourceCallService = resourceCallService;
        this.scheduler = Executors.newScheduledThreadPool(threadPoolSize);
    }

    @Override
    public void add(TcpResource tcpResource) {
        TcpMonitoringTask task = new TcpMonitoringTask(tcpResource, scheduler, resourceCallService);
        tasks.put(tcpResource.getId().getId(), task);
        scheduler.schedule(task, 0, TimeUnit.MILLISECONDS);
    }

    @Override
    public void update(TcpResource tcpResource) {
        TcpMonitoringTask task = tasks.get(tcpResource.getId().getId());
        if (task != null)
            task.cancel();
        task = new TcpMonitoringTask(tcpResource, scheduler, resourceCallService);
        tasks.put(tcpResource.getId().getId(), task);
        scheduler.schedule(task, 0, TimeUnit.MILLISECONDS);
    }

    @Override
    public void delete(TcpResource tcpResource) {
        TcpMonitoringTask task = tasks.get(tcpResource.getId().getId());
        if (task != null)
            task.cancel();
    }


}
