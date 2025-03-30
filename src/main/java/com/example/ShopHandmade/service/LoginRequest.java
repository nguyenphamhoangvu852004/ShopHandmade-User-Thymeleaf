package com.example.ShopHandmade.service;

import groovy.transform.builder.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String email;
    private String password;
}
