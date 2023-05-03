package com.feignpostcommentapi.implmentation;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.feignpostcommentapi.exception.ResourceNotFoundException;
import com.feignpostcommentapi.payloads.CategoryDto;
import com.feignpostcommentapi.repository.CategoryRepository;
import com.feignpostcommentapi.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import com.feignpostcommentapi.entity.Category;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	//Create a Category
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		try{
		Category cat = this.dtoToCategory(categoryDto);
		Category addedCat = this.categoryRepo.save(cat);
		CategoryDto createdCategoryDto = this.categoryToDto(addedCat);
		return createdCategoryDto;
		}catch(IOException io){
			log.error(io.getMessage());
			throw new CategoryException("This category is not created.");
		}	
	}

	//Update a Category
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		try{
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", categoryId));

		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updatedcat = this.categoryRepo.save(cat);
		CategoryDto updatedcatDto = this.categoryToDto(updatedcat);
		return updatedcatDto;
		}catch(IOException io){
			log.error(io.getMessage());
			throw new CategoryException("This category is not updated.");
		}		
	}

	//Delete a Category
	@Override
	public void deleteCategory(Integer categoryId) {
		try{
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "category id", categoryId));
		this.categoryRepo.delete(cat);
		}catch(IOException io){
			log.error(io.getMessage());
			throw new CategoryException("This category is not deleted.");
		}		
	}

	//Get Single Category
	@Override
	public CategoryDto getCategory(Integer categoryId) {
		try{
		Category getcat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		CategoryDto getCategoryDto = this.categoryToDto(getcat);
		return getCategoryDto;
		}catch(IOException io){
			log.error(io.getMessage());
			throw new CategoryException("Unable to get the category.");
		}		
	}

	//Get All Categories
	@Override
	public List<CategoryDto> getCategories() {
		try{
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat) -> this.categoryToDto(cat)).collect(Collectors.toList());
		return catDtos;
		}catch(IOException io){
			log.error(io.getMessage());
			throw new CategoryException("Unable to get all the categories.");
		}		
	}
	
	
	public Category dtoToCategory(CategoryDto categoryDto)
	{
		Category category = new Category();
		category.setCategoryId(categoryDto.getCategoryId());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		return category;
	}

	public CategoryDto categoryToDto(Category category)
	{
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCategoryId(category.getCategoryId());
		categoryDto.setCategoryTitle(category.getCategoryTitle());
		categoryDto.setCategoryDescription(category.getCategoryDescription());
		return categoryDto;
	}


}



