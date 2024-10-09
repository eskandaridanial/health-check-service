package com.heal.ms.application.usecases;

import com.heal.ms.application.commands.DeleteHttpResourceCommand;
import com.heal.ms.application.records.DeleteHttpResourceRecord;
import com.heal.ms.common.application.usecase.BaseUseCase;
import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.common.exception.ResourceNotFoundException;
import com.heal.ms.domain.entities.HttpResource;
import com.heal.ms.domain.repositories.ResourceRepository;
import com.heal.ms.domain.services.MonitoringService;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:05:31
 */
@Component
public class DeleteHttpResourceUseCase implements BaseUseCase<DeleteHttpResourceCommand, DeleteHttpResourceRecord> {

    private final MonitoringService<HttpResource> monitoringService;
    private final ResourceRepository<HttpResource, UniqueId> resourceRepository;

    public DeleteHttpResourceUseCase(MonitoringService<HttpResource> monitoringService, ResourceRepository<HttpResource, UniqueId> resourceRepository) {
        this.monitoringService = monitoringService;
        this.resourceRepository = resourceRepository;
    }

    public DeleteHttpResourceRecord execute(DeleteHttpResourceCommand command) {
        HttpResource httpResource = resourceRepository.findById(new UniqueId(command.resourceId())).orElseThrow(
                () -> new ResourceNotFoundException("Resource with id " + command.resourceId() + " not found."));
        resourceRepository.deleteById(new UniqueId(command.resourceId()));

        if (httpResource != null)
            monitoringService.delete(httpResource);

        return new DeleteHttpResourceRecord(true);
    }
}
