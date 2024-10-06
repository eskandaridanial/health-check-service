package com.heal.ms.common.application.usecase;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:07:42
 */
public interface BaseUseCase<Input, Output> {
    Output execute(Input input);
}
