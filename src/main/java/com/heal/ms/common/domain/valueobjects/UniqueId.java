package com.heal.ms.common.domain.valueobjects;

import de.huxhorn.sulky.ulid.ULID;
import lombok.Getter;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 13:19:04
 */
@Getter
public class UniqueId {

    private final String id;

    public UniqueId() {
        this.id = new ULID().nextULID();
    }

    public UniqueId(String id) {
        this.id = id;
    }
}
