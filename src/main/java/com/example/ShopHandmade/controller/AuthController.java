package com.example.ShopHandmade.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.Validate;

import com.example.ShopHandmade.service.LoginRequest;
import com.example.ShopHandmade.service.RegisterRequest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    // @ResponseBody
    @PostMapping("/processLogin")
    public String processLogin(@Valid @ModelAttribute LoginRequest loginRequest) {
        System.out.println(loginRequest.toString());
        return "redirect:/";
    }

    // @ResponseBody
   @PostMapping("/processRegister")
    public String processRegister(@Valid @ModelAttribute RegisterRequest registerRequest, BindingResult bindingResult, Model model) {
    if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
        bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
    }

    if (bindingResult.hasErrors()) {
        model.addAttribute("registerRequest", registerRequest);
        return "register";
    }

    return "redirect:/";
}


}
