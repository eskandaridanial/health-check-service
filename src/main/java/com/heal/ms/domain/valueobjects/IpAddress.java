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
public class IpAddress {

    private static final Pattern IP_PATTERN = Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}$");

    private String ip;

    public IpAddress(String ip) {
        if (!isValid(ip)) {
            throw new ValidationException(
                    String.format("Invalid IP address: '%s'. Expected a valid IPv4 address (e.g., '192.168.1.1')", ip)
            );
        }
        this.ip = ip;
    }

    private boolean isValid(String ip) {
        return IP_PATTERN.matcher(ip).matches();
    }
}
