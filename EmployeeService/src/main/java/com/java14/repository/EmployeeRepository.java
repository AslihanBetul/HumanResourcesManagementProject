package com.java14.repository;

import com.java14.entity.Employee;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    List<Employee> findAllByManagerId(String managerId);

    Optional<Employee> findByAuthId(Long authId);
}
