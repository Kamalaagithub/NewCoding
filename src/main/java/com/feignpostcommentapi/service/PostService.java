package com.feignpostcommentapi.service;

import java.util.List;

import com.feignpostcommentapi.payloads.PostDto;
import com.feignpostcommentapi.payloads.PostResponse;
import com.feignpostcommentapi.entity.Post;

public interface PostService 
{
	//create
	
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	//update
	
	PostDto updatePost(PostDto postDto, Integer posrtId);
	
	//delete
	
	void deletePost(Integer postId);
	
	//get all posts
	
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//get single Posts
	
	PostDto getPostById(Integer postId);
	
	//get all posts by category
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by user
	
	List<PostDto> getPostsByUser(Integer userId);
	
	//search posts
	
	List<PostDto> searchPosts(String kdeyword);
	
}
