package com.heal.ms.domain.services;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-08 12:16:07
 */
public interface ReportProducerService<ReportAggregate> {

    void produce(ReportAggregate reportAggregate);
}
