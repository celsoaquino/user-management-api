package dev.celsoaquino.usermanagementapi.controller;

import dev.celsoaquino.usermanagementapi.dto.AuthResponse;
import dev.celsoaquino.usermanagementapi.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("auth")
    public ResponseEntity<AuthResponse> authenticate(Authentication authentication) {
        return ResponseEntity.ok(authService.authenticate(authentication));
    }
}
