package com.example.login.service;

import com.example.login.model.User;
import com.example.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignuprServiceImpl implements SignupService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public String registerUser(User user) {
        // Check if user already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "User already exists";
        }
        // Save the new user
        userRepository.save(user);
        return "User registered successfully";
    }
}
