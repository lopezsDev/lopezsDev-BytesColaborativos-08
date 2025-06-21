package com.game.hub.gamehub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {

    private  final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;

        setFilterProcessesUrl("/api/auth/login");
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try{
            String username = obtainUsername(request);
            String password = obtainPassword(request);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            throw new RuntimeException("Error de Autentificación", e.getCause());
        }
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authResult)
        throws IOException {

        try {
            // Get the authenticated user
            User user = (User) authResult.getPrincipal();
            // Generate JWT token
            String token = jwtUtil.generateToken(user.getUsername());
            response.addHeader("Authorization", "Bearer " + token);
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar la autenticación", e);
        }
        //response.addHeader("Access-Control-Expose-Headers", "Authorization");
    }
}
