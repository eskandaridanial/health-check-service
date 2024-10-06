package com.heal.ms.infrastructure.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 18:11:40
 */
@Component
public class ResourceRegistrationJob {
    
    @Scheduled(fixedRateString = "${schedule.periodic.register.fixed-rate.ms}")
    public void registerResource() {
        System.out.println("Registering resource");
    }
}