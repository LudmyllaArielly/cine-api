package com.ludmylla.cineapi.resource;

import com.ludmylla.cineapi.exceptions.CategoryNotFoundException;
import com.ludmylla.cineapi.mapper.CategoryMapper;
import com.ludmylla.cineapi.model.Category;
import com.ludmylla.cineapi.model.dto.CategoryCreateAndListDto;
import com.ludmylla.cineapi.model.dto.CategoryUpdateDto;
import com.ludmylla.cineapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryCreateAndListDto categoryCreateAndListDto){
        Category category = CategoryMapper.INSTANCE.toCategory(categoryCreateAndListDto);
        categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryCreateAndListDto>> findAll(){
        List<Category> list = categoryService.findAll();
        List<CategoryCreateAndListDto> categoryCreateAndListDtos =
                CategoryMapper.INSTANCE.toListDto(list);
        return ResponseEntity.ok(categoryCreateAndListDtos);
    }

    @GetMapping("/{description}")
    public ResponseEntity<CategoryCreateAndListDto> findByDescription(@RequestParam("description") String description){
        Category category = categoryService.findByCategory(description);
        CategoryCreateAndListDto categoryCreateAndListDto = CategoryMapper.INSTANCE.toDto(category);
        return ResponseEntity.ok(categoryCreateAndListDto);
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryUpdateDto categoryUpdateDto){
        Category category = CategoryMapper.INSTANCE.toCategory(categoryUpdateDto);
        categoryService.updateCategory(category);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
