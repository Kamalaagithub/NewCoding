package com.feignpostcommentapi.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="posts")
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
    
    @Column(name = "post_title",length = 100,nullable = false)
    private String title;
    
    @Column(length = 1000000000)
    private String content;
    
    private String imageName;
    
    private Date addedDate;
    
     @ManyToOne
     @JoinColumn(name = "category_id")
     private Category category;
     
     @ManyToOne
     private User user;
     
     @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
     private Set<Comment> comments = new HashSet<>();
     
     

	public Post() {
	
	}

	public Post(Integer postId, String title, String content, String imageName, Date addedDate, Category category,
			User user) {
		
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.category = category;
		this.user = user;
	}
	
	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}




	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddedDate(Date date) {
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
	
}	
	