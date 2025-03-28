package com.example.ShopHandmade.service;


public class AddToCardRequest {
    private int productId;
    private int quantity;
    public AddToCardRequest(short id, int quantity) {
        this.productId = id;
        this.quantity = quantity;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
