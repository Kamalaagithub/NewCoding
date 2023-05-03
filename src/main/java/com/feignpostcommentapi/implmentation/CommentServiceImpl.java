package com.feignpostcommentapi.implmentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.feignpostcommentapi.exception.ResourceNotFoundException;
import com.feignpostcommentapi.payloads.CommentDto;
import com.feignpostcommentapi.payloads.PostDto;
import com.feignpostcommentapi.repository.CommentRepository;
import com.feignpostcommentapi.repository.PostRepository;
import com.feignpostcommentapi.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import com.feignpostcommentapi.entity.Comment;
import com.feignpostcommentapi.entity.Post;
/*
 * CommentServiceImpl implements CommentService is used provide the comments.
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService
{

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
                try{
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id ", postId));

		Comment comment = this.dtoToComment(commentDto);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		CommentDto savedCommentDto = this.commentToDto(savedComment);
		return savedCommentDto;
		}catch(IOException io){
			log.error(io.getMessage());
			throw new CommentException("Comment is not created.");
		}
	}

	@Override
	public void deleteComment(Integer commentId) {
	        try{
		Comment com = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));
		this.commentRepo.delete(com);
		}catch(IOException io){
			log.error(io.getMessage());
			throw new CommentException("Comment is not deleted.");
		}
		
	}
	
	public Comment dtoToComment(CommentDto commentDto)
	{
		Comment comment = new Comment();
		comment.setId(commentDto.getId());
		comment.setContent(commentDto.getContent());
		comment.setPost(commentDto.getPost());
		return comment;
	}

	public CommentDto commentToDto(Comment comment)
	{
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setContent(comment.getContent());
		commentDto.setPost(comment.getPost());
		return commentDto;
	}
}
