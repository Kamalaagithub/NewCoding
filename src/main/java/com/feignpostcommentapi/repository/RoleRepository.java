package com.feignpostcommentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feignpostcommentapi.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	
}
