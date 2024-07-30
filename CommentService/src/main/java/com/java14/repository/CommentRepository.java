package com.java14.repository;

import com.java14.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CommentRepository extends JpaRepository<Comment, Long> {


}
