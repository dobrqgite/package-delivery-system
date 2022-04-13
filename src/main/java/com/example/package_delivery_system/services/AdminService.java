package com.example.package_delivery_system.services;

import com.example.package_delivery_system.data.dtos.employeeDtos.DriverRegisterDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.DriverResponseDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.agentDtos.AgentRegisterDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.agentDtos.AgentResponseDto;

public interface AdminService {
     DriverResponseDto registerDriver(DriverRegisterDto driverDto);

     AgentResponseDto registerAgent(AgentRegisterDto agentDto);

}
