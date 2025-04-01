package com.example.ShopHandmade.service.request;

import groovy.transform.builder.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserRequest {
    private String userId;
    private String phoneNumber;
}
