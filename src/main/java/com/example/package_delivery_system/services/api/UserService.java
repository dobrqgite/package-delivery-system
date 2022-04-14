package com.example.package_delivery_system.services.api;

import com.example.package_delivery_system.data.dtos.userDtos.UserRegisterDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserResponseDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserUpdateDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserResponseDto register(UserRegisterDto userRegisterDto);

    UserResponseDto editCredentials(UserUpdateDto userUpdateDto);

    void seedRoles();
}
