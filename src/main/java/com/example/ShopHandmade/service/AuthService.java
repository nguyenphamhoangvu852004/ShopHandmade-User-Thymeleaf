package com.example.ShopHandmade.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ShopHandmade.service.request.LoginRequest;
import com.example.ShopHandmade.service.request.RegisterRequest;
import com.example.ShopHandmade.service.response.LoginResponse;
import com.example.ShopHandmade.service.response.RegisterResponse;

import groovyjarjarantlr4.v4.parse.ANTLRParser.elementEntry_return;

@Service
public class AuthService {

    private final String userURL = "http://localhost:8082";
    private final RestTemplate restTemplate = new RestTemplate();

    public RegisterResponse doRegister(RegisterRequest registerRequest) {
        String url = userURL + "/api/v1/user";

        // Tạo HttpEntity với body là registerRequest
        HttpEntity<RegisterRequest> requestEntity = new HttpEntity<>(registerRequest);

        // Gọi API và nhận về response dưới dạng RegisterResponse
        ResponseEntity<RegisterResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                RegisterResponse.class);

        return response.getBody();
    }

    public LoginResponse doLogin(LoginRequest loginRequest) {
        String url = userURL + "/api/v1/user-by-email?email=" + loginRequest.getEmail();

        try {
            System.out.println("Calling API: " + url);

            ResponseEntity<LoginResponse> res = restTemplate.getForEntity(url, LoginResponse.class);

            System.out.println("Response Status: " + res.getStatusCode());
            System.out.println("Response Body: " + res.getBody());

            if (res.getStatusCode() == HttpStatus.OK && res.getBody() != null) {
                LoginResponse userResponse = res.getBody();

                PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
                if (passwordEncoder.matches(loginRequest.getPassword(), userResponse.getPassword())) {
                    return userResponse; // Đăng nhập thành công
                } else {
                    System.out.println("Password không khớp");
                }
            } else {
                System.out.println("Không tìm thấy user hoặc lỗi server");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi gọi API: " + e.getMessage());
            e.printStackTrace();
        }

        return null; // Đăng nhập thất bại
    }

}
