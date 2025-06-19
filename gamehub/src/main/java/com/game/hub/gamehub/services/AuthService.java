package com.game.hub.gamehub.services;

import com.game.hub.gamehub.dtos.LoginRequest;
import com.game.hub.gamehub.dtos.LoginResponse;
import com.game.hub.gamehub.dtos.RegisterRequest;
import com.game.hub.gamehub.dtos.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);
    
    LoginResponse login(LoginRequest request);
}
