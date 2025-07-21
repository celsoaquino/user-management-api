package dev.celsoaquino.usermanagementapi.dto;

public record AuthResponse(String token, UserDTO user) {
}
