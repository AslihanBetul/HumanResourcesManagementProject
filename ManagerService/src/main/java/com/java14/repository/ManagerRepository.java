package com.java14.repository;

import com.java14.entity.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;


public interface ManagerRepository extends MongoRepository<Manager, String> {
    Optional<Manager> findByAuthId(Long authId);

    @Query(value = "{ 'authId': ?0 }", fields = "{ 'id': 1 }")
    Optional<Manager> findIdByAuthId(Long authId);
}
