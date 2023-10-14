package com.example.eCommerce.dao;

import com.example.eCommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryDao extends JpaRepository<Category,Integer> {
}
