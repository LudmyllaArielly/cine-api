package com.ludmylla.cineapi.services;

import com.ludmylla.cineapi.model.Category;

import java.util.List;

public interface CategoryService {

    Long createCategory(Category category);

    List<Category> findAll();

    Category findById(Long id);

    Category findByCategory(String description);

    void updateCategory(Category category);

    void deleteCategory(Long id);
}
