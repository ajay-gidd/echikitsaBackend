package com.example.login.controller;

import com.example.login.model.Hospital;
import com.example.login.service.hospital.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospital")
@CrossOrigin
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/register")
    public String registerHospital(@RequestBody Hospital hospital) {
//        String rawPassword = user.getPassword();
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String hashedPassword = encoder.encode(rawPassword);
//        user.setPassword(hashedPassword);

        return hospitalService.registerHospital(hospital);
    }
}
