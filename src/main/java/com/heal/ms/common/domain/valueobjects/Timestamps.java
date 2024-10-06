package com.heal.ms.common.domain.valueobjects;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 13:19:52
 */
@Getter
public class Timestamps {

    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public Timestamps() {
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = this.createdAt;
    }

    public Timestamps(LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Timestamps updateModifiedAt() {
        return new Timestamps(this.createdAt, LocalDateTime.now());
    }
}
