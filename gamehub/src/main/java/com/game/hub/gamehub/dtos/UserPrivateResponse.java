package com.game.hub.gamehub.dtos;

import com.game.hub.gamehub.enums.UserRank;
import com.game.hub.gamehub.enums.UserRole;
import java.util.UUID;

public record UserPrivateResponse(
        UUID id,
        String username,
        String email,
        UserRole role,
        UserRank rank,
        int points
) {}
