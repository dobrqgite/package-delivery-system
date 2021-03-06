package com.example.package_delivery_system.services.api;

import com.example.package_delivery_system.data.dtos.addressDtos.AddressResponseDto;
import com.example.package_delivery_system.data.dtos.addressDtos.AddressUpdateDto;
import com.example.package_delivery_system.data.dtos.userDtos.EditUserCredentialsDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserRegisterDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserResponseDto register(UserRegisterDto userRegisterDto);

    UserResponseDto updateUserDetails(Authentication loggedInUser, EditUserCredentialsDto userUpdateDto);

    AddressResponseDto updateUserAddress(Authentication loggedInUser, AddressUpdateDto addressUpdateDto);

    void seedRoles();
}
