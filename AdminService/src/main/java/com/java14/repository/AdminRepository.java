package com.java14.repository;

import com.java14.entity.Admin;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin,String> {


    Admin findByAuthId(Long authId);
}
