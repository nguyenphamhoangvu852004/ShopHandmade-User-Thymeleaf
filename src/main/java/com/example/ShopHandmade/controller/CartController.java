package com.example.ShopHandmade.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.ShopHandmade.service.GetCart;
import com.example.ShopHandmade.service.ProductService;
import com.example.ShopHandmade.service.request.AddToCardRequest;
import com.example.ShopHandmade.service.response.GetDetailProductResponse;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    private ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/carts")
    public String getCartView(HttpSession httpSession, Model model) {
        List<AddToCardRequest> list = (List<AddToCardRequest>) httpSession.getAttribute("cart");
        String address = "";

        if (list == null) {
            list = new ArrayList<>();
        }

        List<GetCart> listCart = new ArrayList<>();

        for (AddToCardRequest item : list) {
            GetDetailProductResponse product = productService.getProductById(item.getProductId());
            if (product != null) {
                GetCart cart = GetCart.builder()
                        .product(product)
                        .quantity(item.getQuantity())
                        .build();
                listCart.add(cart);
            }
        }

        // for (GetCart cart : listCart) {
        // System.out.println("PRODUCT = " + cart.getProduct().toString() + ", Số lượng
        // = " + cart.getQuantity());
        // }

        model.addAttribute("cart", listCart != null ? listCart : new ArrayList<>());
        model.addAttribute("address", address);
        return "cart";
    }

    @ResponseBody // Trả về JSON hoặc String thay vì Thymeleaf
    @PostMapping("/carts/add")
    public String addToCart(@RequestBody AddToCardRequest cartItem, HttpSession httpSession) {

        List<AddToCardRequest> listCartItems = (List<AddToCardRequest>) httpSession.getAttribute("cart");
        if (listCartItems == null) {
            listCartItems = new ArrayList<>();
        }

        boolean exists = false;

        for (AddToCardRequest item : listCartItems) {
            if (item.getProductId() == cartItem.getProductId()) {
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                exists = true;
                break;
            }
        }

        if (!exists) {
            listCartItems.add(cartItem);
        }

        httpSession.setAttribute("cart", listCartItems);
        System.out.println(
                "Thêm vào giỏ hàng: ID = " + cartItem.getProductId() + ", Số lượng = " + cartItem.getQuantity());
        return "Them San Pham Thanh Cong";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeItemCart(HttpSession httpSession, @PathVariable("id") short id, Model model) {

        List<AddToCardRequest> listCartItems = (List<AddToCardRequest>) httpSession.getAttribute("cart");
        if (listCartItems != null) {
            listCartItems.removeIf(item -> item.getProductId() == id);
            httpSession.setAttribute("cart", listCartItems);
            return "redirect:/carts";
        }
        return "Failed";
    }

    @PostMapping("/cart/quantity/{id}")
    public String changeQuantity(@PathVariable("id") short id,
            @RequestParam("action") String action,
            HttpSession session) {
        List<AddToCardRequest> cart = (List<AddToCardRequest>) session.getAttribute("cart");

        if (cart != null) {
            for (AddToCardRequest item : cart) {
                if (item.getProductId() == id) {
                    if ("increase".equals(action)) {
                        item.setQuantity(item.getQuantity() + 1);
                    } else if ("decrease".equals(action) && item.getQuantity() > 1) {
                        item.setQuantity(item.getQuantity() - 1);
                    }
                    break;
                }
            }
            session.setAttribute("cart", cart); // Cập nhật session
        }

        return "redirect:/carts"; // Quay lại trang giỏ hàng
    }

}
