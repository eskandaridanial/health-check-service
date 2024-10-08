package com.heal.ms.domain.aggregates;

import com.heal.ms.common.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-08 12:17:49
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HttpReportAggregate extends BaseEntity {

    private String resourceId;

    private Boolean isHealthy;

    private Long latency;
}
