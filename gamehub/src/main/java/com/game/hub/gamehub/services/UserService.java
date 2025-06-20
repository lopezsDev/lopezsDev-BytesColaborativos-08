package com.game.hub.gamehub.services;

import com.game.hub.gamehub.dtos.UserPrivateResponse;
import com.game.hub.gamehub.dtos.UserPublicResponse;
import java.util.UUID;

public interface UserService {
    UserPublicResponse getUserById(UUID id);
    UserPrivateResponse getCurrentUser();
}

