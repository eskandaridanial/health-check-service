package com.heal.ms.common.exception;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 15:24:22
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
