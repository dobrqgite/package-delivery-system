package com.example.package_delivery_system.services;

import com.example.package_delivery_system.data.dtos.addressDtos.AddressDto;
import com.example.package_delivery_system.data.dtos.user.UserRegisterDto;

public interface AddressService {

    AddressDto createUserAddress(UserRegisterDto userAddressFromRegisterDto);

}
