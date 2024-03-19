package com.example.login.repository;

import com.example.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long >{



    @Query("SELECT e FROM User e WHERE e.email = :email ")

    User findByEmail(String email);
}
