package com.heal.ms.application.services;

import com.heal.ms.domain.entities.HttpResource;
import com.heal.ms.domain.services.MonitoringService;
import com.heal.ms.domain.services.ResourceCallService;
import com.heal.ms.infrastructure.tasks.HttpMonitoringTask;
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
public class HttpMonitoringService implements MonitoringService<HttpResource> {

    private final ResourceCallService<HttpResource> resourceCallService;
    private final ScheduledExecutorService scheduler;
    private final Map<String, HttpMonitoringTask> tasks = new ConcurrentHashMap<>();

    public HttpMonitoringService(@Value("${http.monitoring.thread-pool.size}") Integer threadPoolSize,
                                 ResourceCallService<HttpResource> resourceCallService) {
        this.resourceCallService = resourceCallService;
        this.scheduler = Executors.newScheduledThreadPool(threadPoolSize);
    }

    @Override
    public void add(HttpResource HttpResource) {
        HttpMonitoringTask task = new HttpMonitoringTask(HttpResource, scheduler, resourceCallService);
        tasks.put(HttpResource.getId().getId(), task);
        scheduler.schedule(task, 0, TimeUnit.MILLISECONDS);
    }

    @Override
    public void update(HttpResource HttpResource) {
        HttpMonitoringTask task = tasks.get(HttpResource.getId().getId());
        if (task != null)
            task.cancel();
        task = new HttpMonitoringTask(HttpResource, scheduler, resourceCallService);
        tasks.put(HttpResource.getId().getId(), task);
        scheduler.schedule(task, 0, TimeUnit.MILLISECONDS);
    }

    @Override
    public void delete(HttpResource HttpResource) {
        HttpMonitoringTask task = tasks.get(HttpResource.getId().getId());
        if (task != null)
            task.cancel();
    }


}
