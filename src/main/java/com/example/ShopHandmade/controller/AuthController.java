package com.example.ShopHandmade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ShopHandmade.service.AuthService;
import com.example.ShopHandmade.service.request.LoginRequest;
import com.example.ShopHandmade.service.request.RegisterRequest;
import com.example.ShopHandmade.service.response.LoginResponse;
import com.example.ShopHandmade.service.response.RegisterResponse;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

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

    @PostMapping("/processLogin")
    public String processLogin(@Valid @ModelAttribute LoginRequest loginRequest, BindingResult bindingResult,
            Model model, HttpSession session) {

        LoginResponse loginResponse = this.authService.doLogin(loginRequest);

        System.out.println(loginRequest.toString());
        if (bindingResult.hasErrors()) {
            model.addAttribute("loginRequest", loginRequest);
            return "login";
        }
        if (loginResponse != null) {
            session.setAttribute("loggedInUser", loginResponse);

            if ("ROLE_ADMIN".equals(loginResponse.getRole())) {
                return "redirect:http://localhost:8083/admin/order/home"; // ADMIN TEMPLATE
            } else {
                return "redirect:/"; // USER TEMPLATE
            }
        }
        return "redirect:/login";
    }

    // @ResponseBody
    @PostMapping("/processRegister")
    public String processRegister(@Valid @ModelAttribute RegisterRequest registerRequest, BindingResult bindingResult,
            Model model) {
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("registerRequest", registerRequest);
            return "register";
        }

        RegisterResponse rs = this.authService.doRegister(registerRequest);

        if (rs != null) {
            System.out.println(rs.toString());
            return "redirect:/";
        } else {
            return "register";
        }

    }

    @GetMapping("/logout")
    public String postMethodName(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

}
