package com.tw.service;

import com.tw.model.Products;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    List getAllProducts();

    Products addProduct(Products products);

    Products findById(UUID id);
}
