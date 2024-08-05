package com.java14.repository;

import com.java14.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository <Auth ,Long >{
    boolean existsByEmail(String email);

    //Optional<Auth> findOptionalByPersonalEmailAndPassword(String personalEmail, String password);


    Optional<Auth> findOptionalByEmailAndPassword(String email, String password);

    Optional<Auth> findOptionalById(Long authId);
}
