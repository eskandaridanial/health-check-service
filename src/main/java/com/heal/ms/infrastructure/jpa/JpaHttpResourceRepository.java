package com.heal.ms.infrastructure.jpa;

import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.domain.entities.HttpResource;
import com.heal.ms.domain.repositories.ResourceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-07 15:29:46
 */
@Component
public interface JpaHttpResourceRepository extends JpaRepository<HttpResource, UniqueId>, ResourceRepository<HttpResource, UniqueId> { }
