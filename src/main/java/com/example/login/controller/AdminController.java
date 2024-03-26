package com.example.login.controller;

import com.example.login.dto.AuthResponseDto;
import com.example.login.dto.AuthStatus;
import com.example.login.model.Doctor;
import com.example.login.model.Hospital;
import com.example.login.model.User;
import com.example.login.repository.HospitalRepository;
import com.example.login.service.AuthService;
import com.example.login.service.admin.adminService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class AdminController {

    private  final adminService adminService;
    private  final HospitalRepository hospitalRepository;

    @PostMapping("/addDoctor/")
    public ResponseEntity<AuthResponseDto> addDoctor(@RequestParam String id, @RequestBody Doctor doctor) {
        System.out.println(id);
        System.out.println(doctor.getEmail());
         Hospital hospital = hospitalRepository.getHospitalById(Long.valueOf(id));
        doctor.setHospital(hospital);

        try {
            adminService.addDoctor(doctor);
            var authResponseDto = new AuthResponseDto("jwtToken", AuthStatus.USER_CREATED_SUCCESSFULLY);

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
