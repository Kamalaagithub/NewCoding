package com.feignpostcommentapi.service;

import com.feignpostcommentapi.payloads.CommentDto;

public interface CommentService 
{
	//Create a Comment
	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	//Delete a Comment
	void deleteComment(Integer commentId);

}

