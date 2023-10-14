package com.example.eCommerce.service;

import com.example.eCommerce.entity.Category;

import java.util.Optional;

public interface CategoryService {


    Optional<Category> getCategoryById(int id);

    Object getAllCategory();

    void addCategory(Category category);

    void deleteById(int id);
}
