package com.example.package_delivery_system.services.api;

import com.example.package_delivery_system.data.dtos.employeeDtos.EmployeeRegisterDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.EmployeeResponseDto;
import com.example.package_delivery_system.data.dtos.userDtos.GetUserInfoDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserBanDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserResponseDto;
import com.example.package_delivery_system.data.dtos.vehicleDtos.GetVehicleInfoDto;

import java.util.List;

public interface AdminService {

     EmployeeResponseDto registerEmployee(EmployeeRegisterDto employeeDto);

     UserResponseDto banUser(UserBanDto userBanDto);

     List<GetUserInfoDto> getCustomerInfo(String userRole);

     List<GetVehicleInfoDto> getVehicles();
}
