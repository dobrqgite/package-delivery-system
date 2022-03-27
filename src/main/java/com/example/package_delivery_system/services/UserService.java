package com.example.package_delivery_system.services;

import com.example.package_delivery_system.data.dtos.UserRegisterDto;

public interface UserService {

    boolean register(UserRegisterDto userRegisterDto);

}
