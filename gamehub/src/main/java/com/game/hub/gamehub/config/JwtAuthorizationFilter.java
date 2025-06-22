package com.game.hub.gamehub.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter{

    private final JwtUtil jwtutil;
    private final UserDetailsService userDetailsService;

    public JwtAuthorizationFilter(JwtUtil jwtutil, UserDetailsService userDetailsService){
        this.jwtutil = jwtutil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{

        String requestHeader = request.getHeader("Authorization");
        String username;
        String token;

        if(requestHeader != null && requestHeader.startsWith("Bearer")){
            token = requestHeader.substring(7);
            try {
                username = jwtutil.extractUsername(token);
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    var userDetails = userDetailsService.loadUserByUsername(username);
                    Boolean validToken = jwtutil.validateToken(token);

                    if(validToken){
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        filterChain.doFilter(request, response);
    }
}
