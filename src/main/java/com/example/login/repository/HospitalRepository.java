package com.example.login.repository;

import com.example.login.model.Hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {

    @Query("SELECT h FROM Hospital h WHERE h.email = :email ")
    Hospital findByEmail(String email);


}
