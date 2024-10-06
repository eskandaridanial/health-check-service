package com.heal.ms.domain.entities;

import com.heal.ms.common.domain.entity.BaseEntity;
import com.heal.ms.domain.valueobjects.IpAddress;
import com.heal.ms.domain.valueobjects.Port;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 13:45:01
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tcp_resources")
public class TcpResource extends BaseEntity {

    @Column(unique = true)
    private String name;

    @Embedded
    private IpAddress ip;

    @Embedded
    private Port port;
}
