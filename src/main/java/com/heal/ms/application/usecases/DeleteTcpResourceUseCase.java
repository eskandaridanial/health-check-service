package com.heal.ms.application.usecases;

import com.heal.ms.application.commands.DeleteTcpResourceCommand;
import com.heal.ms.application.records.DeleteTcpResourceRecord;
import com.heal.ms.application.services.TcpMonitoringService;
import com.heal.ms.common.application.usecase.BaseUseCase;
import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.common.exception.ResourceNotFoundException;
import com.heal.ms.domain.entities.TcpResource;
import com.heal.ms.domain.repositories.TcpResourceRepository;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:05:31
 */
@Component
public class DeleteTcpResourceUseCase implements BaseUseCase<DeleteTcpResourceCommand, DeleteTcpResourceRecord> {

    private final TcpMonitoringService tcpMonitoringService;
    private final TcpResourceRepository tcpResourceRepository;

    public DeleteTcpResourceUseCase(TcpMonitoringService tcpMonitoringService, TcpResourceRepository tcpResourceRepository) {
        this.tcpMonitoringService = tcpMonitoringService;
        this.tcpResourceRepository = tcpResourceRepository;
    }

    public DeleteTcpResourceRecord execute(DeleteTcpResourceCommand command) {
        TcpResource tcpResource = null;
        try {
            tcpResource = tcpResourceRepository.findById(new UniqueId(command.resourceId())).orElseThrow(
                    () -> new ResourceNotFoundException("Resource with id " + command.resourceId() + " not found."));
            tcpResourceRepository.deleteById(new UniqueId(command.resourceId()));
            return new DeleteTcpResourceRecord(true);
        } finally {
            if (tcpResource != null)
                tcpMonitoringService.delete(tcpResource);
        }
    }
}
