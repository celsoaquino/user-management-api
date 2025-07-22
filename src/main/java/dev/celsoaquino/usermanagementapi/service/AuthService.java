package dev.celsoaquino.usermanagementapi.service;

import dev.celsoaquino.usermanagementapi.dto.AuthResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;

    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public AuthResponse authenticate(Authentication authentication) {
        String token = jwtService.generateToken(authentication);
        return new AuthResponse(token, "Bearer", 3600);
    }
}
