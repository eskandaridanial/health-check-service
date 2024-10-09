package com.heal.ms.application.commands;

import com.heal.ms.domain.enums.HttpMethods;

import java.util.Map;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-07 16:25:02
 */
public record UpdateHttpResourceCommand(
        String resourceId,
        String name,
        Long intervalInMs,
        String url,
        HttpMethods httpMethod,
        Map<String, String> headers,
        Map<String, Object> body
) {}