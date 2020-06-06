package com.tw.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Document(collection = "cart")
public class Cart {
    private UUID _id;
    private String customerName;
    private List<OrderDetails> orderDetails;
    private double totalAmount;
}
