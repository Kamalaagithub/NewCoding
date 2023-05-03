package com.feignpostcommentapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.feignpostcommentapi.payloads.ApiResponse;
import com.feignpostcommentapi.payloads.CommentDto;
import com.feignpostcommentapi.service.CommentService;
/* 
 * CommentController is used to create a comment,post a comment, delete a comment.
 */

@RestController
@RequestMapping("/api/v1/")
public class CommentController 
{
	private CommentService commentService;
	
	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId)
	{
		try{
		CommentDto createdComment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
		}catch(IOException ex){
			log.error(ex.getMessage());
		        throw new CommentException("The comment cannot be created");
		}
	}	
		
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) 
	{
              try{
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully !!", true), HttpStatus.OK);
	      }catch(IOException ex){
		      log.error(ex.getMessage());
		      throw new CommentException("The comment cannot be deleted");
	      }     
		      
	}

}
