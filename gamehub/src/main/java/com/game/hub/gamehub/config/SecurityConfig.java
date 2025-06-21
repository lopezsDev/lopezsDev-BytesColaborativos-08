package com.game.hub.gamehub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)
public class SecurityConfig {

        @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public JwtAuthorizationFilter JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService){
        return new JwtAuthorizationFilter(jwtUtil, userDetailsService);
    }

    @Bean
    public JwtAuthenticationFilter JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        JwtAuthenticationFilter auth = new JwtAuthenticationFilter(authenticationManager, jwtUtil);
        auth.setFilterProcessesUrl("/api/auth/login");
        auth.setAuthenticationManager(authenticationManager);
        return auth;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtAuthorizationFilter JwtAuthorizationFilter, JwtAuthenticationFilter JwtAuthenticationFilter) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Solo para pruebas, no recomendado en producción
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                // .requestMatchers("/api/user/**").hasAnyRole("PLAYER","ADMIN")
                // .requestMatchers("/api/tournaments/**").hasRole("ADMIN")
                .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(JwtAuthenticationFilter)
                .addFilterBefore(JwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
