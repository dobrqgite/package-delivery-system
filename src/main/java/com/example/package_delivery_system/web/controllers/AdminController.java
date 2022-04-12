package com.example.package_delivery_system.web.controllers;

import com.example.package_delivery_system.data.dtos.employeeDtos.CreateDriverDto;
import com.example.package_delivery_system.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/admin-home")
    public String adminHome() {
        return "/admin/admin_home";
    }

    @RequestMapping(value = "/register-employee", method = RequestMethod.POST)
    public String registerDriver(CreateDriverDto dto) {
        this.adminService.registerDriver(dto);
        return "/admin/admin_home";
    }




}
