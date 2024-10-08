package com.heal.ms.domain.services;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-08 13:42:39
 */
public interface NotificationService<ReportAggregate> {

    void sendNotification(ReportAggregate reportAggregate);
}
