package com.example.ShopHandmade.service.response;

import groovy.transform.builder.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserResponse {
    private String id;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;
}
