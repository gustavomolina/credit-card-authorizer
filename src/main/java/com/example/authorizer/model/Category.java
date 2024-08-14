package com.example.authorizer.model;

import com.example.authorizer.utils.CategoryType;
import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @Enumerated(EnumType.STRING)
    private CategoryType categoryId;


    private Double totalAmount;

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public CategoryType getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryType categoryId) {
        this.categoryId = categoryId;
    }
}
