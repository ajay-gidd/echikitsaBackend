package com.example.login.service;

import com.example.login.model.User;
import com.example.login.repository.AppUserRepo;
import com.example.login.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements  AuthService{
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepo appUserRepo;



    @Override
    public String login(String email, String password) {
        var authToken = new UsernamePasswordAuthenticationToken(email, password);

        var authenticate = authenticationManager.authenticate(authToken);

        // Generate Token
        return JwtUtils.generateToken(((UserDetails)(authenticate.getPrincipal())).getUsername());
    }

    @Override
    public String signUp(User user) {
        // Check whether user already exists or not
        User user1 =appUserRepo.existsByUsername(user.getEmail());
        if(user1 != null){
            System.out.println(user.getEmail());
            throw new RuntimeException("User already exists");
        }

        // Encode password
        var encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        appUserRepo.save(user);

        //generate token
        return JwtUtils.generateToken(user.getEmail());


    }

    @Override
    public String verifyToken(String token) {
        return null;
    }
}
