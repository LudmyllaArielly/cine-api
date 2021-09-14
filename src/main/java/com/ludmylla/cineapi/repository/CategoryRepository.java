package com.ludmylla.cineapi.repository;

import com.ludmylla.cineapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByDescription(String description);
}
