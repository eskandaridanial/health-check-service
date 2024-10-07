package com.heal.ms.domain.repositories;

import java.util.List;
import java.util.Optional;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:00:15
 */
public interface ResourceRepository<Resource, UniqueId> {
    Resource save(Resource resource);
    List<Resource> findAll();
    Optional<Resource> findById(UniqueId id);
    void deleteById(UniqueId id);
}
