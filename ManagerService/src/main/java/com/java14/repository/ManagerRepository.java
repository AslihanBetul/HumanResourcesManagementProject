package com.java14.repository;

import com.java14.entity.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ManagerRepository extends MongoRepository<Manager, String> {
}
