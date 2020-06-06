package com.tw.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class OrderDetails {
    private String productId;
    private String productQty;
    private Double productPrice;
}
