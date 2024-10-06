package com.heal.ms.domain.repositories;

import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.domain.entities.TcpResource;

import java.util.List;
import java.util.Optional;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:00:15
 */
public interface TcpResourceRepository {
    TcpResource save(TcpResource tcpResource);
    List<TcpResource> findAll();
    Optional<TcpResource> findById(UniqueId id);
    void deleteById(UniqueId id);
}
