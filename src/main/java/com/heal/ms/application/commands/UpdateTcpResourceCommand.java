package com.heal.ms.application.commands;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:06:27
 */
public record UpdateTcpResourceCommand(
        String resourceId,
        String name,
        String ip,
        Integer port,
        Long intervalInMs
) {}
