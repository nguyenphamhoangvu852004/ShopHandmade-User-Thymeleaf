package com.example.ShopHandmade.service.response.order;

import java.util.List;

import lombok.Data;

@Data
public class ProductResponse {
    private int id;
    private String name;
    private String description;
    private double price;
    private String category;
    private List<ImageResponse> images;

}