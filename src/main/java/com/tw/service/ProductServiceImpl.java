package com.tw.service;

import com.tw.exception.ProductException;
import com.tw.model.OrderDetails;
import com.tw.model.Products;
import com.tw.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.tw.exception.ProductException.ExceptionType.OUT_OF_STOCK;

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

    @Override
    public void updateProducts(List<OrderDetails> orderDetailsList) throws ProductException {
        for (OrderDetails order : orderDetailsList) {
            Products product = productRepository.findById(order.getProductId()).get();
            if (isQuantityAvailable(product.getQuantityInStock(), order.getProductQty())) {
                product.setQuantityInStock(product.getQuantityInStock() - order.getProductQty());
                productRepository.save(product);
            }
        }
    }

    private boolean isQuantityAvailable(int quantityInStock, int productQty) throws ProductException {
        if (productQty > quantityInStock)
            throw new ProductException("Quantity is more than Quantity in Stock", OUT_OF_STOCK);
        return true;
    }
}
