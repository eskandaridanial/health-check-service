package com.heal.ms.application.usecases;

import com.heal.ms.application.commands.UpdateHttpResourceCommand;
import com.heal.ms.application.records.UpdateHttpResourceRecord;
import com.heal.ms.common.application.usecase.BaseUseCase;
import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.common.exception.ResourceNotFoundException;
import com.heal.ms.domain.entities.HttpResource;
import com.heal.ms.domain.repositories.ResourceRepository;
import com.heal.ms.domain.services.MonitoringService;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-07 16:26:42
 */
@Component
public class UpdateHttpResourceUseCase implements BaseUseCase<UpdateHttpResourceCommand, UpdateHttpResourceRecord> {

    private final MonitoringService<HttpResource> monitoringService;
    private final ResourceRepository<HttpResource, UniqueId> resourceRepository;

    public UpdateHttpResourceUseCase(MonitoringService<HttpResource> monitoringService, ResourceRepository<HttpResource, UniqueId> resourceRepository) {
        this.monitoringService = monitoringService;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public UpdateHttpResourceRecord execute(UpdateHttpResourceCommand command) {
        HttpResource httpResource = null;
        try {
            httpResource = resourceRepository.findById(new UniqueId(command.resourceId())).orElseThrow(
                    () -> new ResourceNotFoundException("Resource with id " + command.resourceId() + " not found."));

            httpResource.setName(command.name());
            httpResource.setIntervalInMs(command.intervalInMs());
            httpResource.setUrl(command.url());
            httpResource.setHttpMethod(command.httpMethod());
            httpResource.setHeaders(command.headers());
            httpResource.setBody(command.body());

            httpResource = resourceRepository.save(httpResource);

            return new UpdateHttpResourceRecord(
                    httpResource.getId().getId(),
                    httpResource.getName(),
                    httpResource.getIntervalInMs(),
                    httpResource.getUrl(),
                    httpResource.getHttpMethod(),
                    httpResource.getHeaders(),
                    httpResource.getBody(),
                    httpResource.getTimestamps()
            );
        } finally {
            if (httpResource != null)
                monitoringService.update(httpResource);
        }
    }
}
