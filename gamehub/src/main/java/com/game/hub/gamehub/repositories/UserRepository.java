package com.game.hub.gamehub.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.game.hub.gamehub.models.User;

public interface UserRepository extends CrudRepository<User, UUID> {
    List<User> findByEmail(String email);
    User findFirstByEmail(String email);
}
