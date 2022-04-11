package com.example.package_delivery_system.services;

import com.example.package_delivery_system.data.dtos.addressDtos.AddressDto;
import com.example.package_delivery_system.data.dtos.user.UserRegisterDto;
import com.example.package_delivery_system.data.entities.Address;

public interface AddressService {

    Address createUserAddress(UserRegisterDto userAddressFromRegisterDto);

}
