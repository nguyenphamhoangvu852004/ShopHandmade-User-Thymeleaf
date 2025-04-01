package com.example.ShopHandmade.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ShopHandmade.service.request.CreateOrderRequest;
import com.example.ShopHandmade.service.response.order.OrderListResponse;
import com.example.ShopHandmade.service.response.order.OrderResponse;

@Service
public class OrderService {
    private RestTemplate restTemplate = new RestTemplate();
    private String baseUrl = "http://localhost:8051";

    public String createOrder(CreateOrderRequest createOrderRequest, int accountId) {
        String url = baseUrl + "/api/order";

        System.out.println("Cho nay restemplate chuan bi gui di ne" + createOrderRequest.toString());

        HttpEntity<CreateOrderRequest> requestEntity = new HttpEntity<>(createOrderRequest);

        ResponseEntity<String> res = restTemplate.exchange(
                url + "/" + accountId,
                HttpMethod.POST,
                requestEntity,
                String.class);
        String response = res.getBody();
        System.out.println(response);
        return "Created";
    }

    public List<OrderResponse> getAllOrderByUserId(int accountId) {
        String url = baseUrl + "/api/order/" + accountId;

        ResponseEntity<OrderListResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                OrderListResponse.class);

        return response.getBody().getContent(); // Trả về danh sách đơn hàng
    }
}