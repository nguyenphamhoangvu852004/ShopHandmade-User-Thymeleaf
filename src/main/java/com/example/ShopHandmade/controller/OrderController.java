package com.example.ShopHandmade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ShopHandmade.service.OrderService;
import com.example.ShopHandmade.service.request.AddToCardRequest;
import com.example.ShopHandmade.service.request.CreateOrderItemRequest;
import com.example.ShopHandmade.service.request.CreateOrderRequest;
import com.example.ShopHandmade.service.request.LoginRequest;
import com.example.ShopHandmade.service.response.LoginResponse;
import com.example.ShopHandmade.service.response.order.OrderResponse;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public String createOrder(HttpSession httpSession, @RequestParam("address") String address) {
        LoginResponse loggedInUser = (LoginResponse) httpSession.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        String id = loggedInUser.getId().toString();
        System.out.println("flkadsjflksdjf" + id);

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        List<CreateOrderItemRequest> createOrderItemRequests = new ArrayList<>();
        createOrderRequest.setAddress(address);
        List<AddToCardRequest> list = (List<AddToCardRequest>) httpSession.getAttribute("cart");

        for (AddToCardRequest addToCardRequest : list) {
            CreateOrderItemRequest createOrderItemRequest = new CreateOrderItemRequest();
            createOrderItemRequest.setProductId(addToCardRequest.getProductId());
            createOrderItemRequest.setQuantity(addToCardRequest.getQuantity());
            createOrderItemRequests.add(createOrderItemRequest);
        }

        createOrderRequest.setListOrderItem(createOrderItemRequests);

        System.out.println(createOrderRequest.toString());

        String rs = this.orderService.createOrder(createOrderRequest, Integer.parseInt(loggedInUser.getId()));

        System.out.println(
                createOrderRequest.toString());

        return "redirect:/";
    }

    @GetMapping("/orders")
    public String orderVIew(HttpSession session, Model model) {
        LoginResponse loginResponse = (LoginResponse) session.getAttribute("loggedInUser");

        if (loginResponse == null) {
            model.addAttribute("loginRequest", new LoginRequest());
            return "login";
        }
        List<OrderResponse> orders = orderService.getAllOrderByUserId(Integer.parseInt(loginResponse.getId()));

        System.out.println("Vo trang de xem tat car order ne:" + orders.toString());

        model.addAttribute("orders", orders);

        return "order";
    }

}
