package com.example.package_delivery_system.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String viewHome() {
        return "user/DefaultHomePage";
    }

}
