package com.feignpostcommentapi.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoryId;
	
	@Column(name = "title",length = 100, nullable = false)
	private String CategoryTitle;
	
	@Column(name = "description")
	private String categoryDescription;
	
	public Category(Integer categoryId, String categoryTitle, String categoryDescription, List<Post> posts) {
		
		this.categoryId = categoryId;
		this.CategoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
		
	}


	public Category() {
		
	}
	

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return CategoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		CategoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	@OneToMany(mappedBy = "category", cascade =CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
	
	
	
	
	
	
}
