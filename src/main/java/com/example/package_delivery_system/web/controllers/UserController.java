package com.example.package_delivery_system.web.controllers;

import com.example.package_delivery_system.data.dtos.UserRegisterDto;
import com.example.package_delivery_system.data.entities.Role;
import com.example.package_delivery_system.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
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


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String register() {
        return "user/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Object register(UserRegisterDto userToRegister, Model model) {
        this.userService.register(userToRegister);
        model.addAttribute("login", "/user/login");

        return model.getAttribute("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/user/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/edit-profile", method = RequestMethod.GET)
    public String editProfile() {
        return "/user/edit_profile";
    }

    @RequestMapping(value = "/user-index", method = RequestMethod.GET)
    public String getUserIndex() {
        return "/user/user_index";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getLoggedInHomepage() {
        return "/user/profile";
    }

    @GetMapping("/gateway")
    public String gateway(Authentication authentication) {
        //authentication - currently logged in user(principal)
        Collection<Role> userRoles =
                authentication.getAuthorities()
                        .stream()
                        .map(role -> (Role) role)
                        .collect(Collectors.toList());

        for (Role userRole : userRoles) {
            switch (userRole.getAuthority()) {
                case "ADMIN":
                    return "redirect:/admin/admin-home";
                case "CUSTOMER":
                    return "redirect:/user/profile";
                case "AGENT":
                    return "redirect:/agent/agent-home";
                case "DRIVER":
                    return "redirect:/driver/driver-home";
            }
        }
        return "redirect:/";
    }
}