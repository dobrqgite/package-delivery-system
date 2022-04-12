package com.example.package_delivery_system.services;

import com.example.package_delivery_system.data.dtos.employeeDtos.DriverRegisterDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.DriverResponseDto;

public interface AdminService {
     DriverResponseDto registerDriver(DriverRegisterDto driverDto);

}
