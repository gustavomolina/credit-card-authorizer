package com.example.authorizer.repository;

import com.example.authorizer.model.Category;
import com.example.authorizer.utils.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryId(CategoryType categoryType);
}
