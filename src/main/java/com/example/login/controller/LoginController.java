package com.example.login.controller;

import com.example.login.dto.loginCred;
import com.example.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class LoginController {
    @Autowired
    private LoginService loginService;

//    @PostMapping("/login")
//    private boolean loginUser(@RequestBody loginCred logincred)
//    {
//        System.out.println(logincred.getEmail());
//
//        return loginService.authenticate(logincred.getEmail(),logincred.getPassword());
//
//
//    }
    @PostMapping("/login")
    private boolean loginUser(@RequestBody loginCred logincred)
    {
        System.out.println(logincred.getEmail());

        return loginService.authenticate(logincred.getEmail(),logincred.getPassword());


    }
}
