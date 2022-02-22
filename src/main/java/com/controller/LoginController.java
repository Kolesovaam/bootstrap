package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping(value = "login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "logout")
    public String getLogoutPage() {
        return "login";
    }

    @GetMapping(value = "error")
    public static String getErrorPage() {
        return "error";
    }
}
