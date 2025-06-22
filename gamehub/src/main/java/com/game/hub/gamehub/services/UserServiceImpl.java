package com.game.hub.gamehub.services;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.game.hub.gamehub.dtos.UserPrivateResponse;
import com.game.hub.gamehub.dtos.UserPublicResponse;
import com.game.hub.gamehub.models.User;
import com.game.hub.gamehub.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findFirstByEmail(email);
        // Throw exception if user not found.
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado de email" + email);
        }

        List<GrantedAuthority> authorities = Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_" + user.getRole())
        );

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public UserPublicResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return new UserPublicResponse(
                user.getId(),
                user.getUsername(),
                user.getRank(),
                user.getPoints()
        );
    }

    @Override
    public UserPrivateResponse getCurrentUser() {
        // Reemplazar por usuario autenticado cuando Spring Security esté configurado
        UUID mockUserId = UUID.fromString("00000000-0000-0000-0000-000000000001");
        User user = userRepository.findById(mockUserId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return new UserPrivateResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getRank(),
                user.getPoints()
        );
    }
}
