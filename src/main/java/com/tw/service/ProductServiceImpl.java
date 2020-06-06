package com.tw.service;

import com.tw.model.Products;
import com.tw.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Products addProduct(Products products) {
        products.set_id(UUID.randomUUID());
        return productRepository.save(products);
    }

    @Override
    public Products findById(UUID id) {
        return productRepository.findById(id).get();
    }
}
