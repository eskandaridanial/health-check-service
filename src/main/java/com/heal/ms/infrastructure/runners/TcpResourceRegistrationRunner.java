package com.heal.ms.infrastructure.runners;

import com.heal.ms.application.services.TcpMonitoringService;
import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.domain.entities.TcpResource;
import com.heal.ms.domain.repositories.ResourceRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 20:26:28
 */
@Component
public class TcpResourceRegistrationRunner implements ApplicationRunner {

    private final TcpMonitoringService tcpMonitoringService;
    private final ResourceRepository<TcpResource, UniqueId> tcpResourceRepository;

    public TcpResourceRegistrationRunner(TcpMonitoringService tcpMonitoringService, ResourceRepository<TcpResource, UniqueId> tcpResourceRepository) {
        this.tcpMonitoringService = tcpMonitoringService;
        this.tcpResourceRepository = tcpResourceRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        tcpResourceRepository.findAll().forEach(tcpMonitoringService::add);
    }
}
