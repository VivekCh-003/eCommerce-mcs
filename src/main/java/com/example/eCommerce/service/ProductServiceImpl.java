package com.example.eCommerce.service;

import com.example.eCommerce.dao.ProductDao;
import com.example.eCommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void deleteById(long id) {
        productDao.deleteById(id);
    }

    @Override
    public Optional<Product> findById(long id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> getProductsByCategoryId(int id) {
        return productDao.findByCategoryId(id);
    }
}
