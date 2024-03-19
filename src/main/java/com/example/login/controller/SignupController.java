package com.example.login.controller;

import com.example.login.dto.loginCred;
import com.example.login.model.User;
import com.example.login.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SignupController {

    @Autowired
    private SignupService signService;

    @PostMapping("/patient/signup")
    public String registerUser(@RequestBody User user) {
        String rawPassword = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(rawPassword);
        user.setPassword(hashedPassword);

        return signService.registerUser(user);
    }
}
