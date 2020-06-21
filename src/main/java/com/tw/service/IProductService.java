package com.tw.service;

import com.tw.exception.ProductException;
import com.tw.model.OrderDetails;
import com.tw.model.Products;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    List getAllProducts();

    Products addProduct(Products products);

    Products findById(UUID id);

    void updateProducts(List<OrderDetails> orderDetails) throws ProductException;
}
