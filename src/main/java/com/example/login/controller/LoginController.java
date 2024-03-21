package com.example.login.controller;

import com.example.login.dto.AuthRequestDto;
import com.example.login.dto.AuthResponseDto;
import com.example.login.dto.AuthStatus;
import com.example.login.dto.loginCred;
import com.example.login.model.User;
import com.example.login.service.AuthService;
import com.example.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor

public class LoginController {


    private final AuthService authService;
    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody loginCred loginCred) {
        var jwtToken = authService.login(loginCred.getEmail(), loginCred.getPassword());

        var authResponseDto = new AuthResponseDto(jwtToken, AuthStatus.LOGIN_SUCCESS);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authResponseDto);
    }

    @PostMapping("/signUp")
    public ResponseEntity<AuthResponseDto> signUp(@RequestBody User user) {
        try {
            var jwtToken = authService.signUp(user);

            var authResponseDto = new AuthResponseDto(jwtToken, AuthStatus.USER_CREATED_SUCCESSFULLY);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(authResponseDto);
        } catch (Exception e) {
            var authResponseDto = new AuthResponseDto(null, AuthStatus.USER_NOT_CREATED);

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(authResponseDto);
        }

    }





}
