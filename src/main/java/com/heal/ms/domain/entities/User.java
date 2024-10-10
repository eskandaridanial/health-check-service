package com.heal.ms.domain.entities;

import com.heal.ms.common.domain.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
* @author: Danial Eskandari
* @createdAt: 2024-10-11 02:51:51
*/
@Entity
public class User extends BaseEntity {

    @Column
    private String username;

    @Column
    private String password;
}
