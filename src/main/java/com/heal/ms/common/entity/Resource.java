package com.heal.ms.common.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 13:40:13
 */
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "resources")
public abstract class Resource extends BaseEntity {

    @Column(name = "name")
    private String name;
}
