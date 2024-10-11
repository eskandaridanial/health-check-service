package com.heal.ms.application.commands;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-11 09:28:14
 */
public record LoginUserCommand(
        String username,
        String password
) {
}
