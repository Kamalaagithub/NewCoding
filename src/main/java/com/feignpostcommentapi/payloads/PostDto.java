package com.feignpostcommentapi.payloads;

import java.util.Date;

import com.feignpostcommentapi.entity.Category;
import com.feignpostcommentapi.entity.Post;
import com.feignpostcommentapi.entity.User;

public class PostDto
{
  
    private Integer postId;
	
	public Integer getPostId() {
		return postId;
	}


	public void setPostId(Integer postId) {
		this.postId = postId;
	}


	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private Category category;
	
	private User user;

	public PostDto() {
		
	}

	
	public String getImageName() {
		return imageName;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	public Date getAddedDate() {
		return addedDate;
	}


	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public PostDto map(Post post, Class<PostDto> class1) {
		
		return null;
	}
	 
	
}
