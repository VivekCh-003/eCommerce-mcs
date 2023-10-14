package com.example.eCommerce.service;

import com.example.eCommerce.dao.CategoryDao;
import com.example.eCommerce.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryDao categoryDao;

    public void addCategory(Category category){
        categoryDao.save(category);
    }
    public List<Category> getAllCategory(){
        return categoryDao.findAll();
    }

    public void deleteById(int id) {
        Optional<Category> category = categoryDao.findById(id);
        if(category.isPresent()){
            categoryDao.deleteById(id);
        }
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        Optional<Category> category = categoryDao.findById(id);
        return category;
    }
}
