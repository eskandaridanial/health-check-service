package com.heal.ms.domain.aggregates;

import com.heal.ms.common.domain.entity.BaseEntity;
import com.heal.ms.domain.entities.HttpResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-08 12:17:49
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HttpReportAggregate extends BaseEntity implements Serializable {

    private HttpResource resource;

    private Boolean isHealthy;

    private Long latency;
}
