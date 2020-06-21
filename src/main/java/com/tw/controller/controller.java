package com.tw.controller;

import com.tw.exception.ProductException;
import com.tw.model.Cart;
import com.tw.model.Products;
import com.tw.service.CartService;
import com.tw.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shopping-cart")
public class controller {

    @Autowired
    ProductServiceImpl productServiceImpl;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Products> addProduct(@RequestBody Products products) {
        return new ResponseEntity<>(productServiceImpl.addProduct(products), HttpStatus.CREATED);
    }

    @GetMapping("/items/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Products> findById(@PathVariable(value = "id") UUID id) {
        Products byId = productServiceImpl.findById(id);
        return new ResponseEntity<>(byId,HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Products>> showAllProducts(){
        List allProducts = productServiceImpl.getAllProducts();
        return new ResponseEntity<List<Products>>(allProducts,HttpStatus.OK);
    }

    @PostMapping("/cart")
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart) throws ProductException {
        Cart toCart = cartService.saveToCart(cart);
        return new ResponseEntity<>(toCart,HttpStatus.ACCEPTED);
    }

    @GetMapping("/showcart/{mobile}")
    public ResponseEntity<List<Cart>>showCart(@PathVariable (value = "mobile") String mobile) {
        List<Cart> cartList = cartService.showCart(mobile);
        return new ResponseEntity<>(cartList,HttpStatus.OK);
    }

    @GetMapping("cart")
    public ResponseEntity<List<Cart>>allCart() {
        List<Cart> cartList = cartService.allCart();
        return new ResponseEntity<>(cartList,HttpStatus.OK);
    }

    @PostMapping("confirm/{id}")
    public ResponseEntity<String> confirmOrder(@PathVariable (value = "id") UUID id) throws ProductException {
        cartService.confirmOrder(id);
        return new ResponseEntity<>("Order Confirmed",HttpStatus.ACCEPTED);
    }

    @PostMapping("remove/{id}")
    public ResponseEntity<String> removeOrder(@PathVariable (value = "id") UUID id) throws ProductException {
        cartService.removeOrder(id);
        return new ResponseEntity<>("Order Removed",HttpStatus.ACCEPTED);
    }
}