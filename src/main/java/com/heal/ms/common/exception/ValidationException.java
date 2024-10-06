package com.heal.ms.common.exception;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 15:24:22
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
