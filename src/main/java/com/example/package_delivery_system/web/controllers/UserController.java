package com.example.package_delivery_system.web.controllers;

import com.example.package_delivery_system.data.dtos.UserRegisterDto;
import com.example.package_delivery_system.data.entities.Role;
import com.example.package_delivery_system.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/signup")
    public String register() {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String register(UserRegisterDto userToRegister) {
        this.userService.register(userToRegister);
            return "/user/login";
    }

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    @GetMapping("/logged-in-homepage")
    public String getLoggedInHomepage(){
        return "/user/logged_in_homepage";
    }

    @GetMapping("/edit-profile")
    public String editProfile(){
        return "user/edit_profile";
    }

    @GetMapping("/gateway")
    public String gateway(Authentication authentication){
        //authentication - currently logged in user(principal)
        Collection<Role> userRoles =
                authentication.getAuthorities()
                .stream()
                .map(role -> (Role) role)
                .collect(Collectors.toList());

        for (Role userRole : userRoles) {
            if (userRole.getAuthority().equals("ADMIN")){
            return "redirect:/admin/admin-home";
            }
            else if (userRole.getAuthority().equals("CUSTOMER")){
                return "redirect:/user/logged-in-homepage";
            }
            //TODO:ADD other pages for specific roles!
        }
        return "redirect:/";
    }

}