package com.game.hub.gamehub.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.hub.gamehub.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

	User findFirstByEmail(String email); // Find the first user by email

}
