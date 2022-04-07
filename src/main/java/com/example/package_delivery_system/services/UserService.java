package com.example.package_delivery_system.services;

import com.example.package_delivery_system.data.dtos.UserLoginDto;
import com.example.package_delivery_system.data.dtos.UserRegisterDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void register(UserRegisterDto userRegisterDto);

    boolean login(UserLoginDto userLoginDto);

}
