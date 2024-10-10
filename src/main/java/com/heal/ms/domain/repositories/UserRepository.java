package com.heal.ms.domain.repositories;

/**
* @author: Danial Eskandari
* @createdAt: 2024-10-11 03:02:55
*/
public interface UserRepository<User, UniqueId> {

    User save(User user);
}
