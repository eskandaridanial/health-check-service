package com.heal.ms.application.services;

import com.heal.ms.common.domain.valueobjects.UniqueId;
import com.heal.ms.domain.entities.User;
import com.heal.ms.domain.repositories.UserRepository;
import com.heal.ms.domain.valueobjects.Username;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-11 09:18:27
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository<User, UniqueId> userRepository;

    public CustomUserDetailsService(UserRepository<User, UniqueId> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsername(new Username(username))
                .orElseThrow(() -> new UsernameNotFoundException("Wrong credentials."));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername().getUsername(),
                user.getPassword().getPassword(),
                List.of());
    }
}
