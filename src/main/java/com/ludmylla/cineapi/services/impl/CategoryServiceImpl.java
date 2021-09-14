package com.ludmylla.cineapi.services.impl;

import com.ludmylla.cineapi.exceptions.CategoryNotFoundException;
import com.ludmylla.cineapi.model.Category;
import com.ludmylla.cineapi.repository.CategoryRepository;
import com.ludmylla.cineapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Long createCategory(Category category) {
        Category categorySave = categoryRepository.save(category);
        return categorySave.getId();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) throws CategoryNotFoundException{
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category does not exist"));
    }

    @Override
    public Category findByCategory(String description) {
       Category category = categoryRepository.findByDescription(description);
       verifyIfDescriptionOfCategoryExists(category);
       return category;
    }

    @Override
    public void updateCategory(Category category) {
        Category categoryId = findById(category.getId());
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }

    private void verifyIfDescriptionOfCategoryExists(Category category){
        if(category == null){
            throw new CategoryNotFoundException("Category does not exist.");
        }
    }

    private void verifyIfCategoryExits(Long id){
        Category category = findById(id);
        if(category == null){
            throw new CategoryNotFoundException("Category does not exist.");
        }
    }
}
