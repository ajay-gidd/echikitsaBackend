package com.example.login.service;

import com.example.login.model.Hospital;

import com.example.login.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImpl implements HospitalService{

    @Autowired
    private HospitalRepository hospitalRepository;



    @Override
    public String registerHospital(Hospital hospital) {
        // Check if user already exists
        if (hospitalRepository.findByEmail(hospital.getEmail()) != null) {
            return "Hospital already exists";
        }
        // Save the new user
        hospitalRepository.save(hospital);
        return "Hospital registered successfully";
    }
}
