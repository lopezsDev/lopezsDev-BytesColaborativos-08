package com.game.hub.gamehub.dtos;

import com.game.hub.gamehub.enums.UserRank;
import java.util.UUID;

public record UserPublicResponse(
        UUID id,
        String username,
        UserRank rank,
        int points
) {}