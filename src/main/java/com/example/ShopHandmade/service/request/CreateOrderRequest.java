package com.example.ShopHandmade.service.request;

import java.util.List;

import groovy.transform.builder.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderRequest {
    private String address;
    private List<CreateOrderItemRequest> listOrderItem;
}
