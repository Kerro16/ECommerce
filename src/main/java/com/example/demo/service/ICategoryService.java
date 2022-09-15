package com.example.demo.service;

import com.example.demo.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    Category saveCategory(Category category);

    Optional<Category> getCategory(String name);

    List<Category> getCategories();
}
