package com.game.hub.gamehub.mappers;

import com.game.hub.gamehub.dtos.UserPrivateResponse;
import com.game.hub.gamehub.dtos.UserPublicResponse;
import com.game.hub.gamehub.models.User;

public class UserMapper {

    public static UserPrivateResponse toPrivate(User user) {
        return new UserPrivateResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getRank(),
                user.getPoints()
        );
    }

    public static UserPublicResponse toPublic(User user) {
        return new UserPublicResponse(
                user.getId(),
                user.getUsername(),
                user.getRank(),
                user.getPoints()
        );
    }
}
