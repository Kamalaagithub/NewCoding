package com.feignpostcommentapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.feignpostcommentapi.payloads.CategoryDto;
import com.feignpostcommentapi.service.CategoryService;
/*
 * Category Controller is used to category the comment and post.
 */
	
@RestController
@RequestMapping("/api/v1/categories
public class CategoryController
{
	private CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService theCategoryService)
	{
		this.categoryService = theCategoryService;
	}

	//Create a Category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto cateogDto) 
	{
		try{
		CategoryDto createCategory = this.categoryService.createCategory(cateogDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
		}catch(IOException io){
			log.error(io.getMessage());
			throw new CategoryException("This category is not created");
		}	
	}

	//Update a Category
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
			@PathVariable Integer catId) {
		try{
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
		}catch(IOException io){
			log.error(io.getMessage());
			throw new CategoryException("This category is not updated.");
		}	
	}

	//Delete a Category
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) 
	{
		try{
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully !!", true),
				HttpStatus.OK);
		}catch(IOException io){
			log.error(io.getMessage());
			throw new CategoryException("This category is not deleted.");
		}	
	}
	
	//Getting a Category
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId) {
		try{
		CategoryDto categoryDto = this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
		}catch(IOException io){
			log.error(io.getMessage());
			throw new CategoryException("This Category is unable to get from DB.");
		}	

	}

	//Getting  all category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories() {
		try{
		List<CategoryDto> categories = this.categoryService.getCategories();
		return ResponseEntity.ok(categories);
		}catch(IOException io){
			log.error(io.getMessage());
			throw new CategoryException("This category is unable to get from DB.");
		}	
	}

}
