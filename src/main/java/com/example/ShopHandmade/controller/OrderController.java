package com.example.ShopHandmade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("")
public class OrderController {

    // private IOrderService orderService;

    // public OrderController(IOrderService orderService) {
    //     this.orderService = orderService;
    // }

    // @GetMapping("/api/order/{accountId}")
    // public String getAllOrderByAccountId(@PathVariable("accountId") short accountId){
    //     Short id = accountId;
    //     List<GetAllOrderByAccountIdOutputDTO> listOrder = this.orderService.getAllOrdersByAccountId(id);
    //     return listOrder.toString();
    // }

}
