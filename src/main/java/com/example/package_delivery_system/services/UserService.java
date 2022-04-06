package com.example.package_delivery_system.services;

import com.example.package_delivery_system.data.dtos.UserLoginDto;
import com.example.package_delivery_system.data.dtos.UserRegisterDto;
import com.example.package_delivery_system.data.entities.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserService extends UserDetailsService {

    void register(UserRegisterDto userRegisterDto);

    boolean login(UserLoginDto userLoginDto);

}
