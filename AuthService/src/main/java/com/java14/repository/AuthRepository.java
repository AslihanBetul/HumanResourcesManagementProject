package com.java14.repository;

import com.java14.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository <Auth ,Long >{
    boolean existsByEmail(String email);
}
