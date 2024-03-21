package com.example.login.service;

import com.example.login.model.User;
import org.springframework.stereotype.Service;


public interface AuthService {
    String login(String username, String password);

    String signUp(User user);

    String verifyToken(String token);
}