package com.example.package_delivery_system.web.controllers;

import com.example.package_delivery_system.data.dtos.packageDtos.CreatePackageDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserRegisterDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserUpdateDto;
import com.example.package_delivery_system.data.entities.Role;
import com.example.package_delivery_system.data.entities.UserEntity;
import com.example.package_delivery_system.exceptions.impl.PackageServiceImpl;
import com.example.package_delivery_system.exceptions.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserServiceImpl userService;
    private final PackageServiceImpl packageService;

    @Autowired
    public UserController(UserServiceImpl userService, PackageServiceImpl packageService) {
        this.userService = userService;
        this.packageService = packageService;
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

    //TODO: implement method and query from repository
    @RequestMapping(value = "/edit-profile", method = RequestMethod.PUT)
    public UserUpdateDto editCredentials() {
        return null;
    }

    @RequestMapping(value = "/user-index", method = RequestMethod.GET)
    public String getUserIndex() {
        return "/user/user_index";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getLoggedInHomepage() {
        return "/user/profile";
    }

    @RequestMapping(value = "/send-package", method = RequestMethod.GET)
    public String getSendPackagePage() {
        return "/user/send_package";
    }

    @RequestMapping(value = "/send-package", method = RequestMethod.POST)
    public String sendPackage(CreatePackageDto createPackageDto, Authentication authentication) {
        this.packageService.addPackage(createPackageDto, ((UserEntity)authentication.getPrincipal()).getEmail());
        return "/user/send_package";
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