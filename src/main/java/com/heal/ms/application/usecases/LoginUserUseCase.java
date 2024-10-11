package com.heal.ms.application.usecases;

import com.heal.ms.application.services.CustomUserDetailsService;
import com.heal.ms.application.commands.LoginUserCommand;
import com.heal.ms.application.records.LoginUserRecord;
import com.heal.ms.common.application.usecase.BaseUseCase;
import com.heal.ms.utility.JwtUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-11 09:29:13
 */
@Component
public class LoginUserUseCase implements BaseUseCase<LoginUserCommand, LoginUserRecord> {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;


    public LoginUserUseCase(JwtUtils jwtUtils, CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.jwtUtils = jwtUtils;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginUserRecord execute(LoginUserCommand command) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(command.username());
        if (!passwordEncoder.matches(command.password(), userDetails.getPassword()))
            throw new UsernameNotFoundException("Wrong credentials.");

        String token = jwtUtils.generateToken(userDetails);

        return new LoginUserRecord(token);
    }
}
