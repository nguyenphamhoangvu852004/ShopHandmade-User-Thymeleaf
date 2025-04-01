package com.example.ShopHandmade.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ShopHandmade.service.request.UpdateUserRequest;
import com.example.ShopHandmade.service.response.UpdateUserResponse;

@Service
public class UserService {
    private final RestTemplate restTemplate = new RestTemplate();

    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        String url = "http://localhost:8082/api/v1/user";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UpdateUserRequest> requestEntity = new HttpEntity<>(updateUserRequest, headers);

        System.out.println("Sending PUT request to: " + url);
        System.out.println("Request Body: " + updateUserRequest);

        ResponseEntity<UpdateUserResponse> responseEntity = restTemplate.exchange(
                url, HttpMethod.PUT, requestEntity, UpdateUserResponse.class);

        System.out.println("Response: " + responseEntity.getBody());

        return responseEntity.getBody();
    }
}
