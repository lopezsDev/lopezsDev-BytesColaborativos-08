package com.game.hub.gamehub.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.game.hub.gamehub.dtos.LoginRequest;
import com.game.hub.gamehub.dtos.LoginResponse;
import com.game.hub.gamehub.dtos.RegisterRequest;
import com.game.hub.gamehub.dtos.RegisterResponse;
import com.game.hub.gamehub.enums.UserRank;
import com.game.hub.gamehub.enums.UserRole;
import com.game.hub.gamehub.models.User;
import com.game.hub.gamehub.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final Logger logger = LoggerFactory.getLogger(AuthService.class);

	public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public RegisterResponse register(RegisterRequest request) {

		Optional<User> userOpt = userRepository.findByEmail(request.email());
		if (userOpt.isPresent())
			new RuntimeException("Email already used!");

		User user = new User();
		user.setEmail(request.email());
		user.setPassword(passwordEncoder.encode(request.password()));
		user.setUsername(request.username());
		user.setPoints(0);
		user.setRank(UserRank.BRONZE);
		user.setRole(UserRole.PLAYER);
		userRepository.save(user);

		logger.info("User registered:" + user.getUsername());
		

		return new RegisterResponse("User registered");
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		return null;
	}
}
