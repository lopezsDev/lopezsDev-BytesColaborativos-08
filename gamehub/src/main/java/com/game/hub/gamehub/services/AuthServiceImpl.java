package com.game.hub.gamehub.services;

import org.springframework.stereotype.Service;

import com.game.hub.gamehub.dtos.LoginRequest;
import com.game.hub.gamehub.dtos.LoginResponse;
import com.game.hub.gamehub.dtos.RegisterRequest;
import com.game.hub.gamehub.dtos.RegisterResponse;

@Service
public class AuthServiceImpl implements AuthService {


    @Override
    public RegisterResponse register(RegisterRequest request) {
        return null;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        return null;
    }
}

