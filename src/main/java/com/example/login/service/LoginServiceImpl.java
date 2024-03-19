package com.example.login.service;

import com.example.login.model.User;
import com.example.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;



    @Override
    public boolean authenticate(String email, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = userRepository.findByEmail(email);
        return user != null && encoder.matches(password, user.getPassword());
    }
}
