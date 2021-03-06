package com.example.package_delivery_system.web.controllers;

import com.example.package_delivery_system.data.dtos.employeeDtos.EmployeeRegisterDto;
import com.example.package_delivery_system.data.dtos.userDtos.GetUserInfoDto;
import com.example.package_delivery_system.data.dtos.vehicleDtos.CreateVehicleDto;
import com.example.package_delivery_system.data.dtos.vehicleDtos.GetVehicleInfoDto;
import com.example.package_delivery_system.services.api.AdminService;
import com.example.package_delivery_system.services.api.VehicleService;
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
    private final VehicleService vehicleService;

    @Autowired
    public AdminController(AdminService adminService, VehicleService vehicleService) {
        this.adminService = adminService;
        this.vehicleService = vehicleService;
    }


    @RequestMapping(value = "/admin-home", method = RequestMethod.GET)
    public String adminHome(Model model) {
        List<GetUserInfoDto> customers = adminService.getCustomerInfo("CUSTOMER");
        model.addAttribute("customers", customers);

        List<GetUserInfoDto> agents = adminService.getCustomerInfo("AGENT");
        model.addAttribute("agents", agents);

        List<GetUserInfoDto> drivers = adminService.getCustomerInfo("DRIVER");
        model.addAttribute("drivers", drivers);

        List<GetVehicleInfoDto> vehicles = adminService.getVehicles();
        model.addAttribute("vehicles", vehicles);

        return "/admin/admin_home";
    }

    @RequestMapping(value = "/register-employee", method = RequestMethod.POST)
    public String registerEmployee(EmployeeRegisterDto registerUserDto) {
        this.adminService.registerEmployee(registerUserDto);

        return "redirect:/admin/admin-home";
    }

    @RequestMapping(value = "/create-vehicle", method = RequestMethod.POST)
    public String registerVehicle(CreateVehicleDto createVehicleDto) {
        this.vehicleService.createVehicle(createVehicleDto);

        return "redirect:/admin/admin-home";
    }

    @RequestMapping(value = "/admin-index", method = RequestMethod.GET)
    public String getUserIndex() {
        return "/admin/admin_index";
    }
}