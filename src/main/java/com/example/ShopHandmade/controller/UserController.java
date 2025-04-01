package com.example.ShopHandmade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ShopHandmade.service.UserService;
import com.example.ShopHandmade.service.request.UpdateUserRequest;
import com.example.ShopHandmade.service.response.LoginResponse;
import com.example.ShopHandmade.service.response.UpdateUserResponse;

import groovyjarjarantlr4.v4.parse.ANTLRParser.elementEntry_return;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-detail")
    public String getUserDetailPage(HttpSession httpSession, Model model) {
        LoginResponse loggedInUser = (LoginResponse) httpSession.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("user", loggedInUser);
            if (!model.containsAttribute("userUpdate")) {
                model.addAttribute("userUpdate", new UpdateUserRequest());
            }
            return "userInfo";
        }

        return "redirect:/login"; // Điều hướng nếu chưa đăng nhập
    }

    @PostMapping("/update-phone")
    public String updatePhoneNumber(@ModelAttribute UpdateUserRequest updateUserRequest, HttpSession httpSession,
            Model model) {
        System.out.println("Received phoneNumber: " + updateUserRequest.getPhoneNumber()); // Debug giá trị nhận được

        LoginResponse loggedInUser = (LoginResponse) httpSession.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        UpdateUserRequest updateUserReq = new UpdateUserRequest();
        updateUserReq.setUserId(loggedInUser.getId());
        updateUserReq.setPhoneNumber(updateUserRequest.getPhoneNumber());
        System.out.println("IDDDl: " + loggedInUser.getId() + "------" + updateUserRequest.getPhoneNumber());

        UpdateUserResponse updateUserRes = this.userService.updateUser(updateUserReq);

        if (updateUserRes != null) {
            loggedInUser.setPhoneNumber(updateUserRequest.getPhoneNumber());
            httpSession.setAttribute("loggedInUser", loggedInUser);
            model.addAttribute("user", loggedInUser);
            return "redirect:/user-detail";
        } else {
            httpSession.setAttribute("loggedInUser", loggedInUser);
            model.addAttribute("user", loggedInUser);
            return "redirect:/user-detail";
        }

    }

}
