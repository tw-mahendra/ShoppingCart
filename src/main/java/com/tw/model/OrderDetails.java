package com.tw.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class OrderDetails {
    private UUID productId;
    private int productQty;
    private Double productPrice;
}
