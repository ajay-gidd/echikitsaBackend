package com.example.login.repository;

import com.example.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<User,Long > {
    @Query("SELECT e FROM User e WHERE e.email = :email ")
    User existsByUsername(String email);

    @Query("SELECT e FROM User e WHERE e.email = :email ")
    Optional<User> findByUsername(String email);

    @Query("SELECT e FROM User e WHERE e.email = :email ")
    User findByEmail(String email);
}
