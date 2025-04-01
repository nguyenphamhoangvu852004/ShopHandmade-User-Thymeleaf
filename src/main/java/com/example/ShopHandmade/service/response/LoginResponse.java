package com.example.ShopHandmade.service.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String id;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;
}
