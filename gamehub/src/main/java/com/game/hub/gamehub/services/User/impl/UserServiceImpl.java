package com.game.hub.gamehub.services.User.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.hub.gamehub.repositories.UserRepository;

import io.jsonwebtoken.io.IOException;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws IOException {
        com.game.hub.gamehub.models.User user = this.userRepository.findFirstByEmail(email);
        // Throw exception if user not found.
        if (user == null) {
            throw new RuntimeException("Email no encontrado " + email);
        }
        return new User(
            user.getEmail(),
            user.getPassword(),
            null
        );
    }
}
