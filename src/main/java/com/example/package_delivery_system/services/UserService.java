package com.example.package_delivery_system.services;

import com.example.package_delivery_system.data.dtos.user.UserLoginDto;
import com.example.package_delivery_system.data.dtos.user.UserRegisterDto;
import com.example.package_delivery_system.data.dtos.user.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserResponseDto register(UserRegisterDto userRegisterDto);

    void seedRoles();

}
