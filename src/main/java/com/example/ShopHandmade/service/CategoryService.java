package com.example.ShopHandmade.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import groovy.transform.builder.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    private RestTemplate restTemplate = new RestTemplate();

    @Builder
    @Data
    public static class CategoryDTO {
        private String id;
        private String name;

    }

    public List<CategoryDTO> getCategories() {
        String url = "http://localhost:8081/api/v1/category";
        ResponseEntity<CategoryDTO[]> response = restTemplate.getForEntity(url, CategoryDTO[].class);
        return Arrays.asList(response.getBody());
    }
}
