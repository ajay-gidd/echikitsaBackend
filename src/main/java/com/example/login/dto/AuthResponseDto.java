package com.example.login.dto;

public record AuthResponseDto(String token, AuthStatus authStatus) {
}