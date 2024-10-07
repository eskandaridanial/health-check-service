package com.heal.ms.infrastructure.jpa;

import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.domain.entities.TcpResource;
import com.heal.ms.domain.repositories.ResourceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:01:45
 */
@Component
public interface JpaTcpResourceRepository extends JpaRepository<TcpResource, UniqueId>, ResourceRepository<TcpResource, UniqueId> { }
