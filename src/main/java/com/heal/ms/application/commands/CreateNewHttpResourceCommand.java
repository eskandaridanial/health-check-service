package com.heal.ms.application.commands;

import com.heal.ms.domain.enums.HttpMethods;

import java.util.Map;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-07 15:28:00
 */
public record CreateNewHttpResourceCommand(
        String name,
        Long intervalInMs,
        String url,
        HttpMethods httpMethod,
        Map<String, String> headers,
        Map<String, Object> body
) {}
