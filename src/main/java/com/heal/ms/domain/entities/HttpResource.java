package com.heal.ms.domain.entities;

import com.heal.ms.common.domain.entity.BaseEntity;
import com.heal.ms.domain.enums.HttpMethods;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Map;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-07 15:25:20
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "http_resources")
public class HttpResource extends BaseEntity {

    @Column(unique = true)
    private String name;

    @Column
    private Long intervalInMs;

    @Column
    private String url;

    @Enumerated(value = EnumType.STRING)
    private HttpMethods httpMethod;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, String> headers;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> body;
}
