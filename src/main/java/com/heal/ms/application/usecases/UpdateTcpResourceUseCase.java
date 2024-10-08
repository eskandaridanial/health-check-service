package com.heal.ms.application.usecases;

import com.heal.ms.application.commands.UpdateTcpResourceCommand;
import com.heal.ms.application.records.UpdateTcpResourceRecord;
import com.heal.ms.common.application.usecase.BaseUseCase;
import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.common.exception.ResourceNotFoundException;
import com.heal.ms.domain.entities.TcpResource;
import com.heal.ms.domain.repositories.ResourceRepository;
import com.heal.ms.domain.services.MonitoringService;
import com.heal.ms.domain.valueobjects.Host;
import com.heal.ms.domain.valueobjects.Port;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:05:31
 */
@Component
public class UpdateTcpResourceUseCase implements BaseUseCase<UpdateTcpResourceCommand, UpdateTcpResourceRecord> {

    private final MonitoringService<TcpResource> monitoringService;
    private final ResourceRepository<TcpResource, UniqueId> resourceRepository;

    public UpdateTcpResourceUseCase(MonitoringService<TcpResource> monitoringService, ResourceRepository<TcpResource, UniqueId> resourceRepository) {
        this.monitoringService = monitoringService;
        this.resourceRepository = resourceRepository;
    }

    public UpdateTcpResourceRecord execute(UpdateTcpResourceCommand command) {
        TcpResource tcpResource = resourceRepository.findById(new UniqueId(command.resourceId())).orElseThrow(
                () -> new ResourceNotFoundException("Resource with id " + command.resourceId() + " not found."));

        tcpResource.setName(command.name());
        tcpResource.setHost(new Host(command.host()));
        tcpResource.setPort(new Port(command.port()));
        tcpResource.setIntervalInMs(command.intervalInMs());
        tcpResource.setTimeout(command.timeout());

        tcpResource = resourceRepository.save(tcpResource);

        if (tcpResource != null)
            monitoringService.update(tcpResource);

        return new UpdateTcpResourceRecord(
                tcpResource.getId().getId(),
                tcpResource.getName(),
                tcpResource.getIntervalInMs(),
                tcpResource.getHost().getHost(),
                tcpResource.getPort().getPort(),
                tcpResource.getTimeout(),
                tcpResource.getTimestamps()
        );
    }
}
