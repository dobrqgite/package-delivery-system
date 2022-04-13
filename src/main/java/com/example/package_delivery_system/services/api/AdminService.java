package com.example.package_delivery_system.services.api;

import com.example.package_delivery_system.data.dtos.employeeDtos.CreateDriverDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.DriverResponseDto;

public interface AdminService {
     DriverResponseDto registerDriver(CreateDriverDto driverDto);

}
