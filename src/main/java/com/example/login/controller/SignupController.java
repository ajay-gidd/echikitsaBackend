package com.example.login.controller;

import com.example.login.dto.AuthResponseDto;
import com.example.login.dto.AuthStatus;
import com.example.login.model.User;
import com.example.login.service.Auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class SignupController {

    private final AuthService authService;



    @PostMapping("/signUp")
    public ResponseEntity<AuthResponseDto> signUp(@RequestBody User user) {
        try {
            var jwtToken = authService.signUp(user);

            var authResponseDto = new AuthResponseDto(jwtToken, AuthStatus.USER_CREATED_SUCCESSFULLY);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(authResponseDto);
        } catch (Exception e) {
            var authResponseDto = new AuthResponseDto("User Already Exists", AuthStatus.USER_NOT_CREATED);

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(authResponseDto);
        }

    }

}
