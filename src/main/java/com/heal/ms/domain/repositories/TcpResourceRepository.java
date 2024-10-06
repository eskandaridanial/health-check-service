package com.heal.ms.domain.repositories;

import com.heal.ms.domain.entities.TcpResource;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 16:00:15
 */
public interface TcpResourceRepository {
    TcpResource save(TcpResource tcpResource);
    List<TcpResource> findAll();
}
