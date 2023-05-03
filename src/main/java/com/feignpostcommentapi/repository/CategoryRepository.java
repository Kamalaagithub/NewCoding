package com.feignpostcommentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feignpostcommentapi.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer>
{
	
	
	
}
