package com.example.ShopHandmade.service;

import org.codehaus.groovy.runtime.wrappers.PojoWrapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ShopHandmade.model.PageWrapper;
import com.example.ShopHandmade.service.response.GetDetailProductResponse;
import com.example.ShopHandmade.service.response.GetProductResponse;

@Service
public class ProductService {
    private final RestTemplate restTemplate = new RestTemplate();

    public PageWrapper<GetProductResponse> getProducts(int categoryId,int page, int size) {
        String url = "http://localhost:8081/api/v1/product-by?page=" + page + "&size=" + 
        size + "&categoryId=" + categoryId;
        // String url = "http://localhost:8081/api/v1/product";
        ResponseEntity<PageWrapper<GetProductResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageWrapper<GetProductResponse>>() {
                });

        // System.out.println(response.getBody().getContent().toString());

        return response.getBody();
    }

    public GetDetailProductResponse getProductById(int id) {
        String url = "http://localhost:8081/api/v1/product/" + id;

        ResponseEntity<GetDetailProductResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<GetDetailProductResponse>() {
                });
        return response.getBody();
    }
}
