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
}
