package com.example.login.controller;

import com.example.login.dto.AuthResponseDto;
import com.example.login.dto.AuthStatus;
import com.example.login.dto.loginCred;
import com.example.login.model.User;
import com.example.login.service.Auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class LoginController {


    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody loginCred loginCred) {
        var jwtToken = authService.login(loginCred.getEmail(), loginCred.getPassword());

        var authResponseDto = new AuthResponseDto(jwtToken, AuthStatus.LOGIN_SUCCESS);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authResponseDto);
    }



}
