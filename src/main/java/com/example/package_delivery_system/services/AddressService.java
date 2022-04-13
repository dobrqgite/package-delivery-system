package com.example.package_delivery_system.services;

import com.example.package_delivery_system.data.dtos.employeeDtos.EmployeeRegisterDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserRegisterDto;
import com.example.package_delivery_system.data.entities.Address;

public interface AddressService {

    Address createUserAddress(UserRegisterDto userAddressFromRegisterDto);

    Address createUserAddress(EmployeeRegisterDto employeeAddressFromRegisterDto);

}
