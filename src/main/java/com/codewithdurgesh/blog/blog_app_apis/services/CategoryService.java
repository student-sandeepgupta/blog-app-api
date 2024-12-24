package com.codewithdurgesh.blog.blog_app_apis.services;

import java.util.List;

import com.codewithdurgesh.blog.blog_app_apis.payloads.CategoryDto;

public interface CategoryService {

    //Create
    CategoryDto createCategory(CategoryDto categoryDto);
    //Update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
    //Delete
    void deleteCategory(Integer categoryId );

    //Get
    List<CategoryDto> getCategories();

 
   //GetById
   CategoryDto getCategoryById(Integer categoryId );

}
