package com.tw.service;

import com.tw.model.Cart;
import com.tw.model.OrderDetails;
import com.tw.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public Cart saveToCart(Cart cart) {
        cart.set_id(UUID.randomUUID());
        cart.setTotalAmount(cart.getOrderDetails()
                .stream()
                .filter(obj -> obj.getProductPrice() != null)
                .mapToDouble(OrderDetails::getProductPrice)
                .sum());
        return cartRepository.save(cart);
    }
}
