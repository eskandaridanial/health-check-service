package com.heal.ms.domain.valueobjects;

import com.heal.ms.common.exception.ValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-06 15:22:46
 */
@Getter
@NoArgsConstructor
public class Host {

    private static final Pattern IP_PATTERN = Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}$");
    private static final Pattern HOSTNAME_PATTERN = Pattern.compile("^(?!-)[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.?[A-Za-z]{2,}$");

    private String host;

    public Host(String host) {
        if (!isValid(host)) {
            throw new ValidationException(
                    String.format("Invalid host: '%s'. Expected a valid IPv4 address (e.g., '192.168.1.1') or a valid hostname", host)
            );
        }
        this.host = host;
    }

    private boolean isValid(String input) {
        return IP_PATTERN.matcher(input).matches() || HOSTNAME_PATTERN.matcher(input).matches();
    }
}
