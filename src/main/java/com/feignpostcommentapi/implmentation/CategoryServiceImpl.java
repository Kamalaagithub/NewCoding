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
		Category cat = this.dtoToCategory(categoryDto);
		Category addedCat = this.categoryRepo.save(cat);
		CategoryDto createdCategoryDto = this.categoryToDto(addedCat);
		return createdCategoryDto;
	}

	//Update a Category
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", categoryId));

		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updatedcat = this.categoryRepo.save(cat);
		CategoryDto updatedcatDto = this.categoryToDto(updatedcat);
		return updatedcatDto;
	}

	//Delete a Category
	@Override
	public void deleteCategory(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "category id", categoryId));
		this.categoryRepo.delete(cat);
	}

	//Get Single Category
	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category getcat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		CategoryDto getCategoryDto = this.categoryToDto(getcat);
		return getCategoryDto;
	}

	//Get All Categories
	@Override
	public List<CategoryDto> getCategories() {

		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat) -> this.categoryToDto(cat)).collect(Collectors.toList());
		return catDtos;
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



