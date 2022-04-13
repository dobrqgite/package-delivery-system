package com.example.package_delivery_system.services;

import com.example.package_delivery_system.data.dtos.employeeDtos.EmployeeRegisterDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.EmployeeResponseDto;

public interface AdminService {

     EmployeeResponseDto registerEmployee(EmployeeRegisterDto employeeDto);

}
