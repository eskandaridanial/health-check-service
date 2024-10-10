package com.heal.ms.domain.entities;

import com.heal.ms.common.domain.entity.BaseEntity;
import com.heal.ms.domain.valueobjects.Password;
import com.heal.ms.domain.valueobjects.Username;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @author: Danial Eskandari
* @createdAt: 2024-10-11 02:51:51
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class User extends BaseEntity {

    @Embedded
    private Username username;

    @Embedded
    private Password password;
}
