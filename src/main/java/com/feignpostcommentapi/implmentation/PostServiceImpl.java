package com.feignpostcommentapi.implmentation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.feignpostcommentapi.exception.ResourceNotFoundException;
import com.feignpostcommentapi.payloads.PostDto;
import com.feignpostcommentapi.payloads.PostResponse;
import com.feignpostcommentapi.repository.CategoryRepository;
import com.feignpostcommentapi.repository.PostRepository;
import com.feignpostcommentapi.repository.UserRepository;
import com.feignpostcommentapi.service.PostService;
import lombok.extern.slf4j.Slf4j;
import com.feignpostcommentapi.entity.Category;
import com.feignpostcommentapi.entity.Post;
import com.feignpostcommentapi.entity.User;

@Service
@Slf4j
public class PostServiceImpl implements PostService
{
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;

	
	//Creating a Post
	@Override
	public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) 
	{
	    try{
	    User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User id", userId));
	    
	    Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
	    
	    Post post = this.dtoToPost(postDto);
		post.setImageName("default.png");
	    post.setAddedDate(new Date());
	    post.setUser(user);
	    post.setCategory(category);
		
		Post savedPost = this.postRepo.save(post);
		PostDto savedPostDto = this.postToDto(savedPost);
		return savedPostDto;
		}catch(IOException io){
			log.error(io.getMessage());
			throw new PostException("This post is not created");
		}	
		
	}
	
	//Updating a Post
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId)
	{
                try{
        	Post post = this.postRepo.findById(postId)
                	.orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));

       	 	Category category = this.categoryRepo.findById(postDto.getCategory().getCategoryId()).get();

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		post.setCategory(category);

		Post updatedPost = this.postRepo.save(post);
		PostDto updatedPostDto = this.postToDto(updatedPost);
		return updatedPostDto;
		}catch(IOException io){
			log.error(io.getMessage());
			throw new PostException("This post is not updated.");
		}	
    }

	//Deleting a Post
	@Override
	public void deletePost(Integer postId) {
                try{
		Post post = this.postRepo.findById(postId)
			.orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));

		this.postRepo.delete(post);
		}catch(IOException io){
			log.error(io.getMessage());
			throw new PostException("This post is not deleted.");
		}	

    	}

	//Get All Posts
	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
	        try{
		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = this.postRepo.findAll(p);

		List<Post> allPosts = pagePost.getContent();

		List<PostDto> postDtos = allPosts.stream().map((post) ->  this.postToDto(post))
			.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
		}catch(IOException io){
			log.error(io.getMessage());
			throw new PostException("Unable to fetch all post.");
		}	
    	}

	//Get Posts by Id
	@Override
	 public PostDto getPostById(Integer postId) {
		try{
		Post post = this.postRepo.findById(postId)
			.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		PostDto getPostByIdDto = this.postToDto(post);
		return getPostByIdDto;
		}catch(IOException io){
			log.error(io.getMessage());
			throw new PostException("Unable to fetch by using postid.");
		}	
	    }

	//Get Posts by Category
	@Override
	 public List<PostDto> getPostsByCategory(Integer categoryId) {
                try{
		Category cat = this.categoryRepo.findById(categoryId)
			.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);

		List<PostDto> postDtos = posts.stream().map((post) -> this.postToDto(post))
			.collect(Collectors.toList());
		return postDtos;
		}catch(IOException io){
			log.error(io.getMessage());
			throw new PostException("Unable to fetch by categoryid.");
		}	
    	}
	
	
	//Get Posts By User
	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
                try{
		User user = this.userRepo.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User ", "userId ", userId));
		List<Post> posts = this.postRepo.findByUser(user);

		List<PostDto> postDtos = posts.stream().map((post) ->  this.postToDto(post)).collect(Collectors.toList());
		return postDtos;
		}catch(IOException io){
			log.error(io.getMessage());
			throw new PostException("Unable to fetch by userid.");
		}	
   	 }

	//To search a Post
	@Override
	 public List<PostDto> searchPosts(String keyword) {
	        try{
		List<Post> posts = this.postRepo.searchByTitle("%" + keyword + "%");
		List<PostDto> postDtos = posts.stream().map((post) ->  this.postToDto(post)).collect(Collectors.toList());
		return postDtos;
		}catch(IOException io){
			log.error(io.getMessage());
			throw new PostException("Unable to search a post.");
		}
    	}
	
	public Post dtoToPost(PostDto postDto)
	{
		Post post = new Post();
		post.setPostId(postDto.getPostId());
		post.setImageName(postDto.getImageName());
		post.setTitle(postDto.getTitle());
		post.setAddedDate(postDto.getAddedDate());
		post.setCategory(postDto.getCategory());
		post.setUser(postDto.getUser());
		return post;
	}

	public PostDto postToDto(Post post)
	{
		PostDto postDto = new PostDto();
		postDto.setPostId(postDto.getPostId());
		postDto.setImageName(postDto.getImageName());
		postDto.setTitle(postDto.getTitle());
		postDto.setAddedDate(postDto.getAddedDate());
		postDto.setCategory(postDto.getCategory());
		postDto.setUser(postDto.getUser());
		return postDto;
	}

}
