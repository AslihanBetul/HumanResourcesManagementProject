package com.java14.repository;


import com.java14.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {

   @Query(value = "{ 'name' : { $regex: ?0, $options: 'i' } }")
   Optional<Company> findByNameIgnoreCase(String name);


   String findManagerIdById(String companyId);
}
