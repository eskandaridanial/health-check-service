package com.heal.ms.application.records;

import com.heal.ms.common.domain.valueobjects.Timestamps;
import com.heal.ms.domain.enums.HttpMethods;

import java.util.Map;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-07 16:25:02
 */
public record UpdateHttpResourceRecord(
        String resourceId,
        String name,
        Long intervalInMs,
        String url,
        HttpMethods httpMethod,
        Map<String, Object> headers,
        Map<String, Object> body,
        Timestamps timestamps
) {}