package com.feignpostcommentapi.implmentation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.feignpostcommentapi.exception.ResourceNotFoundException;
import com.feignpostcommentapi.payloads.UserDto;
import com.feignpostcommentapi.repository.UserRepository;
import com.feignpostcommentapi.service.UserService;

import lombok.extern.slf4j.Slf4j;

import com.feignpostcommentapi.entity.User;

/*
 * UserServiceImpl is the Implementation of UserService for creating,updating,deleting,retrieving user.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto)
	{
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		UserDto createdUser = this.userToDto(savedUser);
		return createdUser;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		
		return this.userToDto(user);
		
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
		
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepo.findById(userId)
		                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		this.userRepo.delete(user);
		
	}
	
	public User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		return user;
	}

	public UserDto userToDto(User user)
	{
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}
	
}