package com.feignpostcommentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feignpostcommentapi.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	
}