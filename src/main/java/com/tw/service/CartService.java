package com.tw.service;

import com.tw.exception.ProductException;
import com.tw.model.Cart;
import com.tw.model.OrderDetails;
import com.tw.model.OrderStatus;
import com.tw.model.Products;
import com.tw.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.tw.exception.ProductException.ExceptionType.*;
import static com.tw.model.OrderStatus.CONFIRMED;
import static com.tw.model.OrderStatus.IN_CART;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductServiceImpl productService;

//    public Cart saveToCart(Cart cart) {
//        cart.set_id(UUID.randomUUID());
//        cart.setTotalAmount(cart.getOrderDetails()
//                .stream()
//                .filter(obj -> obj.getProductPrice() != null)
//                .mapToDouble(OrderDetails::getProductPrice)
//                .sum());
//        return cartRepository.save(cart);
//    }

    public Cart saveToCart(Cart cart) throws ProductException {
        cart.set_id(UUID.randomUUID());
        List<OrderDetails> orderDetailsList = cart.getOrderDetails();
        double sum = 0;
        for (OrderDetails orders : orderDetailsList) {
            sum = sum + (orders.getProductQty() * orders.getProductPrice());
        }
        cart.setTotalAmount(sum);
        cart.setStatus(IN_CART);

        for (OrderDetails order : orderDetailsList) {
            Products product = productService.findById(order.getProductId());
            if (order.getProductQty() > product.getQuantityInStock() ) {
                throw new ProductException("Quantity is more than Quantity in Stock", MORE_THAN_QUANTITY);
            }
        }
        cartRepository.save(cart);
        return cart;
    }

    public List<Cart> showCart(String mobile) {
        return cartRepository.findByMobile(mobile);
    }

    public List<Cart> allCart() {
        return cartRepository.findAll();
    }

    public void confirmOrder(UUID id) throws ProductException {
        Cart cart = cartRepository.findById(id).get();
        if (!isValidOrderId(id, cart.get_id()))
            throw new ProductException("Invalid Order", INVALID_ORDER);

        if (isOrderInCart(cart.getStatus())) {
            productService.updateProducts(cart.getOrderDetails());
            cart.setStatus(CONFIRMED);
            cartRepository.save(cart);
        }
    }

    private boolean isValidOrderId(UUID id, UUID cart_id) {
        if (!cart_id.equals(id))
            return false;
        return true;
    }

    private Boolean isOrderInCart(OrderStatus status) throws ProductException {
        if (status.equals(IN_CART))
            return true;
        throw new ProductException("Order Already Placed", ALREADY_PLACED);
    }

    public void removeOrder(UUID id) throws ProductException {
        if (!cartRepository.existsById(id))
            throw new ProductException("Order is Not Present", ORDER_NOT_PRESENT);
        cartRepository.deleteById(id);
    }
}
