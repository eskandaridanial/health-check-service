package com.heal.ms.domain.entities;

import com.heal.ms.common.entity.Resource;
import com.heal.ms.domain.valueobjects.IpAddress;
import com.heal.ms.domain.valueobjects.Port;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 13:45:01
 */
@Getter
@Entity
@Table(name = "tcp_resources")
public class TcpResource extends Resource {

    @Embedded
    private IpAddress ip;

    @Embedded
    private Port port;
}
