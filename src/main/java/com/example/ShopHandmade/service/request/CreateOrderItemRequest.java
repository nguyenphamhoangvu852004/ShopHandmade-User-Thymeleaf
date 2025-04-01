package com.example.ShopHandmade.service.request;

import groovy.transform.builder.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderItemRequest {
    private int productId;
    private int quantity;
}
