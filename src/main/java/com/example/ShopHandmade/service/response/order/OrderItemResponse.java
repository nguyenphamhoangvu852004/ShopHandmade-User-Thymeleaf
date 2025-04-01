package com.example.ShopHandmade.service.response.order;

import lombok.Data;

@Data
public class OrderItemResponse {
    private int id;
    private ProductResponse product;
    private int quantity;
    private double totalPrice;

}
