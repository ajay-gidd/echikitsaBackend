package com.example.login.repository;

import com.example.login.model.Doctor;
import com.example.login.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    @Query("SELECT d FROM Doctor d WHERE d.hospital.hospital_id = 2")
    void addHospital();
}
