package com.example.ShopHandmade.service.response.order;

import java.util.List;

import lombok.Data;

@Data
public class OrderResponse {
    private int id;
    private String status;
    private String orderDate;
    private String address;
    private List<OrderItemResponse> listOrderItems;
    private double totalAmount;

}
