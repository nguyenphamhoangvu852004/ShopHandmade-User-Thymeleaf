package com.example.ShopHandmade.service;

import com.example.ShopHandmade.service.response.GetDetailProductResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCart {
    private GetDetailProductResponse product;
    private int quantity;
}
