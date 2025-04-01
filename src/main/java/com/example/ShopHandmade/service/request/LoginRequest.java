package com.example.ShopHandmade.service.request;

import groovy.transform.builder.Builder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Not valid email")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String password;
}
