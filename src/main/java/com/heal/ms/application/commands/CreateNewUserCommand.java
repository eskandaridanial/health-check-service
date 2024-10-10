package com.heal.ms.application.commands;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-11 03:04:27
 */
public record CreateNewUserCommand(
        String username,
        String password
) {}
