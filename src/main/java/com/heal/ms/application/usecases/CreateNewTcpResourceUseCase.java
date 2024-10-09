package com.heal.ms.application.usecases;

import com.heal.ms.application.commands.CreateNewTcpResourceCommand;
import com.heal.ms.application.records.CreateNewTcpResourceRecord;
import com.heal.ms.common.application.usecase.BaseUseCase;
import com.heal.ms.common.domain.valueobjects.UniqueId;
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
public class CreateNewTcpResourceUseCase implements BaseUseCase<CreateNewTcpResourceCommand, CreateNewTcpResourceRecord> {

    private final MonitoringService<TcpResource> monitoringService;
    private final ResourceRepository<TcpResource, UniqueId> resourceRepository;

    public CreateNewTcpResourceUseCase(MonitoringService<TcpResource> monitoringService, ResourceRepository<TcpResource, UniqueId> resourceRepository) {
        this.monitoringService = monitoringService;
        this.resourceRepository = resourceRepository;
    }

    public CreateNewTcpResourceRecord execute(CreateNewTcpResourceCommand command) {
        // todo: handle rollback in case of persistence stage
        TcpResource tcpResource = resourceRepository.save(new TcpResource(
                        command.name(),
                        command.intervalInMs(),
                        new Host(command.host()),
                        new Port(command.port()),
                        command.timeout()
                ));

        monitoringService.add(tcpResource);

        return new CreateNewTcpResourceRecord(
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
