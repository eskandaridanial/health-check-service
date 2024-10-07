package com.heal.ms.application.usecases;

import com.heal.ms.application.commands.UpdateTcpResourceCommand;
import com.heal.ms.application.records.UpdateTcpResourceRecord;
import com.heal.ms.application.services.TcpMonitoringService;
import com.heal.ms.common.application.usecase.BaseUseCase;
import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.common.exception.ResourceNotFoundException;
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
public class UpdateTcpResourceUseCase implements BaseUseCase<UpdateTcpResourceCommand, UpdateTcpResourceRecord> {

    private final TcpMonitoringService tcpMonitoringService;
    private final ResourceRepository<TcpResource, UniqueId> tcpResourceRepository;

    public UpdateTcpResourceUseCase(TcpMonitoringService tcpMonitoringService, ResourceRepository<TcpResource, UniqueId> tcpResourceRepository) {
        this.tcpMonitoringService = tcpMonitoringService;
        this.tcpResourceRepository = tcpResourceRepository;
    }

    public UpdateTcpResourceRecord execute(UpdateTcpResourceCommand command) {
        TcpResource tcpResource = null;
        try {
            tcpResource = tcpResourceRepository.findById(new UniqueId(command.resourceId())).orElseThrow(
                    () -> new ResourceNotFoundException("Resource with id " + command.resourceId() + " not found."));

            tcpResource.setName(command.name());
            tcpResource.setIp(new IpAddress(command.ip()));
            tcpResource.setPort(new Port(command.port()));
            tcpResource.setIntervalInMs(command.intervalInMs());
            tcpResource.setTimeout(command.timeout());

            tcpResource = tcpResourceRepository.save(tcpResource);

            return new UpdateTcpResourceRecord(
                    tcpResource.getId().getId(),
                    tcpResource.getName(),
                    tcpResource.getIntervalInMs(),
                    tcpResource.getIp().getIp(),
                    tcpResource.getPort().getPort(),
                    tcpResource.getTimeout(),
                    tcpResource.getTimestamps()
            );
        } finally {
            if (tcpResource != null)
                tcpMonitoringService.update(tcpResource);
        }
    }
}
