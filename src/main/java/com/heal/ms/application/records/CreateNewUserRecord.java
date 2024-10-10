package com.heal.ms.application.records;

import com.heal.ms.common.domain.valueobjects.Timestamps;

/**
* @author: Danial Eskandari
* @createdAt: 2024-10-11 03:06:22
*/
public record CreateNewUserRecord(
        String userId,
        String username,
        Timestamps timestamps
) {}
