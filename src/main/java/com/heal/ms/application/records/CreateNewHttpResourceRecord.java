package com.heal.ms.application.records;

import com.heal.ms.common.domain.valueobjects.Timestamps;

import java.util.Map;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-07 15:32:05
 */
public record CreateNewHttpResourceRecord(
        String resourceId,
        String name,
        Long intervalInMs,
        String url,
        String httpMethod,
        Map<String, Object> headers,
        Map<String, Object> body,
        Timestamps timestamps
) {}
