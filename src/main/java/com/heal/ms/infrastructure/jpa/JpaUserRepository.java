package com.heal.ms.infrastructure.jpa;

import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.domain.entities.User;
import com.heal.ms.domain.repositories.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-11 03:02:55
 */
@Component
public interface JpaUserRepository extends JpaRepository<User, UniqueId>, UserRepository<User, UniqueId> { }
