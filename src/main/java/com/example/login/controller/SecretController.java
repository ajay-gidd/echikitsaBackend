package com.example.login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/auth/secret")
public class SecretController {


    @GetMapping("/")
    public ResponseEntity<String> getSecret(){
        System.out.println("Hey");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(UUID.randomUUID().toString());
    }
}
