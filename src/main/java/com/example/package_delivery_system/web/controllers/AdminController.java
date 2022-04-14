package com.example.package_delivery_system.web.controllers;

import com.example.package_delivery_system.data.dtos.employeeDtos.EmployeeRegisterDto;
import com.example.package_delivery_system.data.entities.UserEntity;
import com.example.package_delivery_system.data.repositories.UserRepository;
import com.example.package_delivery_system.services.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserRepository userRepository;

    @Autowired
    public AdminController(AdminService adminService, UserRepository userRepository) {
        this.adminService = adminService;
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "/admin-home", method = RequestMethod.GET)
    public String adminHome(Model model) {

        List<UserEntity> allCustomers = this.userRepository.findAll();
        model.addAttribute("customers", allCustomers);

        return "/admin/admin_home";
    }

    @RequestMapping(value = "/admin-home", method = RequestMethod.POST)
    public String registerEmployee(EmployeeRegisterDto dto) {
        this.adminService.registerEmployee(dto);

        return "/admin/admin_home";
    }

    @RequestMapping(value = "/admin-index", method = RequestMethod.GET)
    public String getUserIndex() {
        return "/admin/admin_index";
    }

}