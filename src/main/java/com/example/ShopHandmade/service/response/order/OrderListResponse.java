package com.example.ShopHandmade.service.response.order;

import java.util.List;

public class OrderListResponse {
    private List<OrderResponse> content;

    public List<OrderResponse> getContent() {
        return content;
    }

    public void setContent(List<OrderResponse> content) {
        this.content = content;
    }
}
