package com.heal.ms.domain.valueobjects;

import com.heal.ms.common.exception.ValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 15:29:18
 */
@Getter
@NoArgsConstructor
public class Port {

    private static final Integer MIN = 1;
    private static final Integer MAX = 65535;

    private Integer port;

    public Port(Integer port) {
        if (!isValid(port)) {
            throw new ValidationException(
                    String.format("Invalid port number: %d. Port numbers must be in the range of 1 to 65535.", port)
            );
        }
        this.port = port;
    }

    private boolean isValid(Integer port) {
        return port < MIN && port > MAX;
    }
}
