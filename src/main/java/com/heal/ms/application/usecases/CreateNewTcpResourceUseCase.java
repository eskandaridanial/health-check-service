package com.heal.ms.application.usecases;

import com.heal.ms.application.commands.CreateNewTcpResourceCommand;
import com.heal.ms.application.records.CreateNewTcpResourceRecord;
import com.heal.ms.application.services.TcpMonitoringService;
import com.heal.ms.common.application.usecase.BaseUseCase;
import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.domain.entities.TcpResource;
import com.heal.ms.domain.repositories.ResourceRepository;
import com.heal.ms.domain.valueobjects.IpAddress;
import com.heal.ms.domain.valueobjects.Port;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:05:31
 */
@Component
public class CreateNewTcpResourceUseCase implements BaseUseCase<CreateNewTcpResourceCommand, CreateNewTcpResourceRecord> {

    private final TcpMonitoringService tcpMonitoringService;
    private final ResourceRepository<TcpResource, UniqueId> tcpResourceRepository;

    public CreateNewTcpResourceUseCase(TcpMonitoringService tcpMonitoringService, ResourceRepository<TcpResource, UniqueId> tcpResourceRepository) {
        this.tcpMonitoringService = tcpMonitoringService;
        this.tcpResourceRepository = tcpResourceRepository;
    }

    public CreateNewTcpResourceRecord execute(CreateNewTcpResourceCommand command) {
        TcpResource tcpResource = null;
        try {
            // todo: handle rollback in case of persistence stage
            tcpResource = tcpResourceRepository.save(new TcpResource(
                    command.name(),
                    command.intervalInMs(),
                    new IpAddress(command.ip()),
                    new Port(command.port()),
                    command.timeout()
            ));

            return new CreateNewTcpResourceRecord(
                    tcpResource.getId().getId(),
                    tcpResource.getName(),
                    tcpResource.getIntervalInMs(),
                    tcpResource.getIp().getIp(),
                    tcpResource.getPort().getPort(),
                    tcpResource.getTimeout(),
                    tcpResource.getTimestamps()
            );
        } finally {
            tcpMonitoringService.add(tcpResource);
        }
    }
}
