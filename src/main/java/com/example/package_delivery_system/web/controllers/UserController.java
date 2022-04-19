package com.example.package_delivery_system.web.controllers;

import com.example.package_delivery_system.data.dtos.addressDtos.AddressUpdateDto;
import com.example.package_delivery_system.data.dtos.packageDtos.CreatePackageDto;
import com.example.package_delivery_system.data.dtos.userDtos.EditUserCredentialsDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserRegisterDto;
import com.example.package_delivery_system.data.entities.Address;
import com.example.package_delivery_system.data.entities.Package;
import com.example.package_delivery_system.data.entities.Role;
import com.example.package_delivery_system.data.entities.UserEntity;
import com.example.package_delivery_system.data.repositories.AddressRepository;
import com.example.package_delivery_system.data.repositories.PackageRepository;
import com.example.package_delivery_system.services.impl.PackageServiceImpl;
import com.example.package_delivery_system.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserServiceImpl userService;
    private final PackageServiceImpl packageService;
    private final PackageRepository packageRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public UserController(UserServiceImpl userService, PackageServiceImpl packageService,
            PackageRepository packageRepository, AddressRepository addressRepository) {
        this.userService = userService;
        this.packageService = packageService;
        this.packageRepository = packageRepository;
        this.addressRepository = addressRepository;
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
    public String editProfile(Authentication authentication, Model model) {
        Address address = ((UserEntity) authentication.getPrincipal()).getAddress();
        UserEntity userEntity = ((UserEntity) authentication.getPrincipal());

        model.addAttribute("address", address);
        model.addAttribute("loggedInUser", userEntity);

        return "/user/edit_profile";
    }

    @RequestMapping(value = "/edit-profile", method = RequestMethod.POST)
    public String editCredentials(Authentication authentication, EditUserCredentialsDto editedUserCredentialsDto) {
        userService.updateUserDetails(authentication, editedUserCredentialsDto);
        return "redirect:/user/edit-profile";
    }

    @RequestMapping(value = "/edit-address", method = RequestMethod.POST)
    public String editAddress(Authentication authentication, AddressUpdateDto addressUpdateDto) {
        userService.updateUserAddress(authentication, addressUpdateDto);
        return "redirect:/user/edit-profile";
    }

    @RequestMapping(value = "/user-index", method = RequestMethod.GET)
    public String getUserIndex(Model model, Authentication authentication) {

        UserEntity userEntity = ((UserEntity) authentication.getPrincipal());

        model.addAttribute("loggedInUser", userEntity);

        return "/user/user_index";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getLoggedInHomepage(Model model, Authentication authentication) {

        // TODO: make model return packages by userId
        List<Package> packages = packageRepository.findAll().stream()
                .collect(Collectors.toUnmodifiableList());
        UserEntity userEntity = ((UserEntity) authentication.getPrincipal());

        model.addAttribute("loggedInUser", userEntity);
        model.addAttribute("packages", packages);
        // might go boom
        model.addAttribute("packagesToReceive", authentication.getPrincipal());
        return "/user/profile";
    }

    @RequestMapping(value = "/send-package", method = RequestMethod.GET)
    public String getSendPackagePage(Model model, Authentication authentication) {

        UserEntity userEntity = ((UserEntity) authentication.getPrincipal());

        model.addAttribute("loggedInUser", userEntity);
        return "/user/send_package";
    }

    @RequestMapping(value = "/send-package", method = RequestMethod.POST)
    public String sendPackage(CreatePackageDto createPackageDto, Authentication authentication) {
        this.packageService.addPackage(createPackageDto, ((UserEntity) authentication.getPrincipal()).getEmail());
        return "redirect:/user/send-package";
    }

    @GetMapping("/gateway")
    public String gateway(Authentication authentication) {
        // authentication - currently logged in user(principal)
        Collection<Role> userRoles = authentication.getAuthorities()
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