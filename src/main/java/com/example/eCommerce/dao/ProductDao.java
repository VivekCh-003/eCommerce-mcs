package com.example.eCommerce.dao;

import com.example.eCommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Long> {
    List<Product> findByCategoryId(int id);
}
