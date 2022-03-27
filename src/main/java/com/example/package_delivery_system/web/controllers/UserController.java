package com.example.package_delivery_system.web.controllers;

import com.example.package_delivery_system.data.dtos.UserRegisterDto;
import com.example.package_delivery_system.services.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController extends BaseController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/customers/signup")
    public String register(){
        return "user/signup";
    }

    //TODO: FIX!NOT SAVING USERS IN DB AND NOT REDIRECTING!!!
    @PostMapping("/customers/signup")
    public String register(UserRegisterDto userToRegister, Model model){
        if (this.userService.register(userToRegister)){
            return "redirect:/www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley";
        }

        model.addAttribute("error", "Something went horribly wrong!");

        return "/customers/signup";
    }

}
