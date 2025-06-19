package com.game.hub.gamehub.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
    // this is accord to jjwtoken v 0.12.6
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.password(jwtSecret.toCharArray());
    }

    // Generate token
    public String generateToken(String username){
        Date now = new Date();
        Date exp = new Date(now.getTime() + this.jwtExpiration);
        return Jwts.builder()
            .subject(username)
            .issuedAt(now)
            .expiration(exp)
            .signWith(key)
            .compact();
    }

    // validate token
    public boolean validateToken(String token){
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // extract the payload subject content
    public String extractUsername(String token){
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
