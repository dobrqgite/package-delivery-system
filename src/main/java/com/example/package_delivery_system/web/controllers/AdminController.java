package com.example.package_delivery_system.web.controllers;

import com.example.package_delivery_system.data.dtos.employeeDtos.DriverRegisterDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.agentDtos.AgentRegisterDto;
import com.example.package_delivery_system.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    @RequestMapping(value = "/admin-home", method = RequestMethod.GET)
    public String adminHome() {
        return "/admin/admin_home";
    }

//    @RequestMapping(value = "/admin-home", method = RequestMethod.POST)
//    public String registerDriver(DriverRegisterDto dto) {
//        this.adminService.registerDriver(dto);
//        return "/admin/admin_home";
//    }

    @RequestMapping(value = "/admin-home", method = RequestMethod.POST)
    public String registerAgent(AgentRegisterDto dto) {
        this.adminService.registerAgent(dto);
        return "/admin/admin_home";
    }


 /*  MAYBE? mapping
  @RequestMapping(value = "/admin-home", method = RequestMethod.POST)
    public String registerEmployee(AgentRegisterDto agentDto, DriverRegisterDto driverDto) {
        if(){
        this.adminService.registerDriver(driverDto)
        }else{
        this.adminService.registerAgent(agentDto);
        }
        return "/admin/admin_home";
   } */

}

