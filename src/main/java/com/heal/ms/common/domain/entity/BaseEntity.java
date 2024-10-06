package com.heal.ms.common.domain.entity;

import com.heal.ms.common.domain.valueobjects.Timestamps;
import com.heal.ms.common.domain.valueobjects.UniqueId;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 13:15:51
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @EmbeddedId
    private UniqueId id;

    @Embedded
    private Timestamps timestamps;

    protected BaseEntity() {
        this.id = new UniqueId();
        this.timestamps = new Timestamps();
    }

    public void updateModifiedAt() {
        this.timestamps = this.timestamps.updateModifiedAt();
    }

    @PreUpdate
    protected void onUpdate() {
        updateModifiedAt();
    }
}
