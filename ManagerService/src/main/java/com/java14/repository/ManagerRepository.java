package com.java14.repository;

import com.java14.entity.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ManagerRepository extends MongoRepository<Manager, String> {
    Optional<Manager> findByAuthId(Long authId);
}
