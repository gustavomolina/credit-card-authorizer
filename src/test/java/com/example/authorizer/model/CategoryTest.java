package com.example.authorizer.model;

import com.example.authorizer.utils.CategoryType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    @Test
    void categorySetterGetter() {
        Category category = new Category();
        category.setTotalAmount(100.0);
        category.setCategoryId(CategoryType.FOOD);

        assertEquals(100.0, category.getTotalAmount());
        assertEquals(CategoryType.FOOD, category.getCategoryId());
    }
}
