package com.heal.ms.application.records;

import com.heal.ms.common.domain.valueobjects.Timestamps;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:09:16
 */
public record UpdateTcpResourceRecord(
        String resourceId,
        String name,
        Long intervalInMs,
        String ip,
        Integer port,
        Integer timeout,
        Timestamps timestamps
) { }