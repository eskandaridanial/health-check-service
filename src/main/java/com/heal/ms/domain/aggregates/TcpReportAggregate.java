package com.heal.ms.domain.aggregates;

import com.heal.ms.common.domain.entity.BaseEntity;
import com.heal.ms.domain.entities.TcpResource;
import lombok.*;

import java.io.Serializable;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-08 12:17:49
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TcpReportAggregate extends BaseEntity implements Serializable {

    private TcpResource resource;

    private Boolean isHealthy;

    private Long latency;
}
