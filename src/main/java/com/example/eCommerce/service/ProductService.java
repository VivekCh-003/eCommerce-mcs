package com.example.eCommerce.service;

import com.example.eCommerce.entity.Product;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    void save(Product product);

    void deleteById(long id);

    Optional<Product> findById(long id);

    List<Product> getProductsByCategoryId(int id);
}
