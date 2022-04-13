package com.example.package_delivery_system.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping("/admin-home")
    public String adminHome() {
        return "/admin/admin_home";
    }

    @RequestMapping(value = "/register-driver", method = RequestMethod.POST)
    public String registerDriver() {
        return "/admin/regisrer_driver";
    }


}
