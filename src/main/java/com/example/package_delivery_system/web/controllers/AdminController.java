package com.example.package_delivery_system.web.controllers;

import com.example.package_delivery_system.data.dtos.employeeDtos.EmployeeRegisterDto;
import com.example.package_delivery_system.data.dtos.userDtos.GetUserInfoDto;
import com.example.package_delivery_system.data.entities.UserEntity;
import com.example.package_delivery_system.data.repositories.UserRepository;
import com.example.package_delivery_system.services.api.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminController(AdminService adminService, UserRepository userRepository, ModelMapper modelMapper) {
        this.adminService = adminService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @RequestMapping(value = "/admin-home", method = RequestMethod.GET)
    public String adminHome() {
        return "/admin/admin_home";
    }

    @RequestMapping(value = "/admin-home", method = RequestMethod.POST)
    public String registerEmployee(EmployeeRegisterDto registerUserDto, Model model) {
        this.adminService.registerEmployee(registerUserDto);

        List<GetUserInfoDto> customers = this.userRepository.findAll()
                .stream()
                .filter(user -> user.getRoles().stream().anyMatch(role -> role.getAuthority().equals("CUSTOMER")))
                .map(user -> this.modelMapper.map(user, GetUserInfoDto.class))
                .collect(Collectors.toList());

        model.addAttribute("customers", customers);

        List<GetUserInfoDto> agents = this.userRepository.findAll()
                .stream()
                .filter(user -> user.getRoles().stream().anyMatch(role -> role.getAuthority().equals("AGENT")))
                .map(user -> this.modelMapper.map(user, GetUserInfoDto.class))
                .collect(Collectors.toList());

        model.addAttribute("agents", agents);

        List<GetUserInfoDto> drivers = this.userRepository.findAll()
                .stream()
                .filter(user -> user.getRoles().stream().anyMatch(role -> role.getAuthority().equals("DRIVER")))
                .map(user -> this.modelMapper.map(user, GetUserInfoDto.class))
                .collect(Collectors.toList());

        model.addAttribute("drivers", drivers);

        return "/admin/admin_home";
    }

    @RequestMapping(value = "/admin-index", method = RequestMethod.GET)
    public String getUserIndex() {
        return "/admin/admin_index";
    }

}