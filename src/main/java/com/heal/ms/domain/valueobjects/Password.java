package com.heal.ms.domain.valueobjects;

import com.heal.ms.common.exception.ValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-11 02:55:29
 */
@Getter
@NoArgsConstructor
public class Password {

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 32;

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String password;

    public Password(String rawPassword) {
        validatePassword(rawPassword);
        this.password = hashPassword(rawPassword);
    }

    private void validatePassword(String rawPassword) {
        if (rawPassword == null || rawPassword.trim().isEmpty()) {
            throw new ValidationException("Password cannot be null or empty.");
        }
        if (rawPassword.length() < MIN_LENGTH || rawPassword.length() > MAX_LENGTH) {
            throw new ValidationException("Password must be between " + MIN_LENGTH + " and " + MAX_LENGTH + " characters.");
        }
    }

    private String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean matches(String rawPassword) {
        return passwordEncoder.matches(rawPassword, this.password);
    }
}
