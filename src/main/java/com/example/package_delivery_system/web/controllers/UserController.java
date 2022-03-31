package com.example.package_delivery_system.web.controllers;

import com.example.package_delivery_system.data.dtos.UserRegisterDto;
import com.example.package_delivery_system.services.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/customers")
public class UserController extends BaseController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/signup")
    public String register() {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String register(UserRegisterDto userToRegister, Model model) {
        if (this.userService.register(userToRegister)) {
            return "user/login";
        }

        model.addAttribute("error", "Something went horribly wrong!");

        return "/user/signup";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    //TODO: add login functionality


    @GetMapping("/edit_profile")
    public String editProfile(){
        return "user/edit_profile";
    }
    //TODO: add edit profile functionality!


    @GetMapping("/logged_in_homepage")
    public String getLoggedInHomepage(){
        return "user/logged_in_homepage";
    }
}