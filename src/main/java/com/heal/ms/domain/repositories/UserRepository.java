package com.heal.ms.domain.repositories;

import com.heal.ms.domain.valueobjects.Username;

import java.util.Optional;

/**
* @author: Danial Eskandari
* @createdAt: 2024-10-11 03:02:55
*/
public interface UserRepository<User, UniqueId> {

    User save(User user);
    Optional<User> findByUsername(Username username);
}
