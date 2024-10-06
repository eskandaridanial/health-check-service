package com.heal.ms.domain.services;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 20:19:32
 */
public interface ResourceCallService<Resource> {

    void call(Resource resource);
}
