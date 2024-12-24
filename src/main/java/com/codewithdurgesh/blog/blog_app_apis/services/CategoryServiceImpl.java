package com.codewithdurgesh.blog.blog_app_apis.services;

import java.util.List;
//import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.blog_app_apis.entities.Category;
import com.codewithdurgesh.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.blog_app_apis.payloads.CategoryDto;
import com.codewithdurgesh.blog.blog_app_apis.repositories.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService
{

@Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
  Category cat =  this.modelMapper.map(categoryDto, Category.class);
 Category addedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat, CategoryDto.class);
    }
 
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
          Category cat  = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
     cat.setCategoryTitle(categoryDto.getCategoryTitle());
      cat.setCategoryDescription(categoryDto.getCategoryDescription());
          Category updatedCat = this.categoryRepo.save(cat);
          return this.modelMapper.map(updatedCat, CategoryDto.class);
        }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","category id",categoryId));
      this.categoryRepo.delete(cat);
    }


    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","category id" , categoryId));
           
        return this.modelMapper.map(cat, CategoryDto.class);
        }

        
    @Override
    public List<CategoryDto> getCategories() {
      List<Category> categories =  this.categoryRepo.findAll();
     List<CategoryDto> catDtos =  categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
            return catDtos;
        }

}
