package com.tw.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Document(collection = "products")
public class Products {

    private UUID _id;
    private String productName;
    private String description;
    private int quantityInStock;
    private double price;

//    public Products(String productName, String description, int quantityInStock, double price) {
//        this._id = UUID.randomUUID();
//        this.productName = productName;
//        this.description = description;
//        this.quantityInStock = quantityInStock;
//        this.price = price;
//    }
//
//    public UUID get_id() {
//        return _id;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public int getQuantityInStock() {
//        return quantityInStock;
//    }
//
//    public double getPrice() {
//        return price;
//    }
}
