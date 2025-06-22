package com.game.hub.gamehub.controllers;

import com.game.hub.gamehub.dtos.UserPrivateResponse;
import com.game.hub.gamehub.dtos.UserPublicResponse;
import com.game.hub.gamehub.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserPublicResponse> getUserById(@PathVariable UUID id) {
        UserPublicResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/me")
    public ResponseEntity<UserPrivateResponse> getCurrentUser() {
        // Restringir acceso solo a roles PLAYER y ADMIN cuando Spring Security esté configurado
        UserPrivateResponse response = userService.getCurrentUser();
        return ResponseEntity.ok(response);
    }
}
