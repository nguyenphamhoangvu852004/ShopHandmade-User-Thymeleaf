package com.example.ShopHandmade.service;

import groovy.transform.builder.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    private String username;
    private String password;
    private String confirmPassword;
}
