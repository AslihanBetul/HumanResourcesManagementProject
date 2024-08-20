package com.java14.repository;

import com.java14.entity.Comment;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface CommentRepository extends MongoRepository<Comment, String> {



    Optional<Comment> findByCompanyId(String companyId);

    Optional<Comment> findByManagerId(String managerID);
}
