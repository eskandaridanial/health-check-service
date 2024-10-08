package com.heal.ms.infrastructure.runners;

import com.heal.ms.application.services.HttpMonitoringService;
import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.domain.entities.HttpResource;
import com.heal.ms.domain.repositories.ResourceRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 20:26:28
 */
@Component
public class HttpResourceRegistrationRunner implements ApplicationRunner {

    private final HttpMonitoringService httpMonitoringService;
    private final ResourceRepository<HttpResource, UniqueId> resourceRepository;

    public HttpResourceRegistrationRunner(HttpMonitoringService httpMonitoringService, ResourceRepository<HttpResource, UniqueId> resourceRepository) {
        this.httpMonitoringService = httpMonitoringService;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        resourceRepository.findAll().forEach(httpMonitoringService::add);
    }
}
