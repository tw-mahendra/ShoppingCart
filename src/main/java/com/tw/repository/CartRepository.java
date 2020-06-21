package com.tw.repository;

import com.tw.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface CartRepository extends MongoRepository<Cart, UUID> {
    List<Cart> findByMobile(String mobile);
}
