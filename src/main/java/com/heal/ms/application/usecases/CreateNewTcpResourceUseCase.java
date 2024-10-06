package com.heal.ms.application.usecases;

import com.heal.ms.application.commands.CreateNewTcpResourceCommand;
import com.heal.ms.application.records.CreateNewTcpResourceRecord;
import com.heal.ms.common.application.usecase.BaseUseCase;
import com.heal.ms.domain.entities.TcpResource;
import com.heal.ms.domain.repositories.TcpResourceRepository;
import com.heal.ms.domain.valueobjects.IpAddress;
import com.heal.ms.domain.valueobjects.Port;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:05:31
 */
@Component
public class CreateNewTcpResourceUseCase implements BaseUseCase<CreateNewTcpResourceCommand, CreateNewTcpResourceRecord> {

    private final TcpResourceRepository tcpResourceRepository;

    public CreateNewTcpResourceUseCase(TcpResourceRepository tcpResourceRepository) {
        this.tcpResourceRepository = tcpResourceRepository;
    }

    public CreateNewTcpResourceRecord execute(CreateNewTcpResourceCommand command) {
        TcpResource tcpResource = tcpResourceRepository.save(new TcpResource(
                command.name(),
                new IpAddress(command.ip()),
                new Port(command.port()),
                command.intervalInMs()
        ));
        return new CreateNewTcpResourceRecord(
                tcpResource.getId().getId(),
                tcpResource.getName(),
                tcpResource.getIp().getIp(),
                tcpResource.getPort().getPort(),
                tcpResource.getIntervalInMs(),
                tcpResource.getTimestamps()
        );
    }
}
