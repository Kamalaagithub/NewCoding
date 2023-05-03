package com.feignpostcommentapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feignpostcommentapi.payloads.ApiResponse;
import com.feignpostcommentapi.payloads.UserDto;
import com.feignpostcommentapi.service.UserService;

/*
 * UserController is used to create,update,delete,get all the users inside the API.
 */
@RestController
@RequestMapping("/api/users")
public class UserController
{
	
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService theUserService) {
	
		this.userService = theUserService;
	}
	
	//POST - Create User
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
	{
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
		
	}
	
	//PUT - Update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId)
	{
		UserDto updateduser = this.userService.updateUser(userDto,userId);
		return ResponseEntity.ok(updateduser);
	}
 
	//Delete - Delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId)
	{
		this.deleteUser(userId);
		return new ResponseEntity<ApiResponse>((HttpStatusCode) new ApiResponse("User Deleted Successfully", true, HttpStatus.OK));
	}
	
	
}
