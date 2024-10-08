package com.heal.ms.application.usecases;

import com.heal.ms.application.commands.CreateNewHttpResourceCommand;
import com.heal.ms.application.records.CreateNewHttpResourceRecord;
import com.heal.ms.common.application.usecase.BaseUseCase;
import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.domain.entities.HttpResource;
import com.heal.ms.domain.repositories.ResourceRepository;
import com.heal.ms.domain.services.MonitoringService;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-07 15:29:46
 */
@Component
public class CreateNewHttpResourceUseCase implements BaseUseCase<CreateNewHttpResourceCommand, CreateNewHttpResourceRecord> {

    private final MonitoringService<HttpResource> monitoringService;
    private final ResourceRepository<HttpResource, UniqueId> resourceRepository;

    public CreateNewHttpResourceUseCase(MonitoringService<HttpResource> monitoringService, ResourceRepository<HttpResource, UniqueId> resourceRepository) {
        this.monitoringService = monitoringService;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public CreateNewHttpResourceRecord execute(CreateNewHttpResourceCommand command) {
        HttpResource httpResource = null;
        try {
            // todo: handle rollback in case of persistence stage
            httpResource = resourceRepository.save(new HttpResource(
                    command.name(),
                    command.intervalInMs(),
                    command.url(),
                    command.httpMethod(),
                    command.headers(),
                    command.body()
            ));

            return new CreateNewHttpResourceRecord(
                    httpResource.getId().getId(),
                    httpResource.getName(),
                    httpResource.getIntervalInMs(),
                    httpResource.getUrl(),
                    httpResource.getHttpMethod().name(),
                    httpResource.getHeaders(),
                    httpResource.getBody(),
                    httpResource.getTimestamps()
            );
        } finally {
            monitoringService.add(httpResource);
        }
    }
}
