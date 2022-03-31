package com.example.package_delivery_system.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/admin/admin_home")
    public String viewAdminHome(){
        return "admin/admin_home";
    }

    @GetMapping("/admin/admin_login")
    public String viewAdminLogin(){
        return "admin/admin_login";
    }
}
