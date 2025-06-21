package com.game.hub.gamehub.services;

import com.game.hub.gamehub.dtos.UserPrivateResponse;
import com.game.hub.gamehub.dtos.UserPublicResponse;
import com.game.hub.gamehub.models.User;
import com.game.hub.gamehub.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
