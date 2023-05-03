package com.feignpostcommentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feignpostcommentapi.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>
{

}
