package com.heal.ms.application.records;

import com.heal.ms.common.domain.valueobjects.Timestamps;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:09:16
 */
public record CreateNewTcpResourceRecord(
        String resourceId,
        String name,
        String ip,
        Integer port,
        Timestamps timestamps
) { }