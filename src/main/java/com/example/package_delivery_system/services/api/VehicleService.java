package com.example.package_delivery_system.services.api;

import com.example.package_delivery_system.data.dtos.vehicleDtos.CreateVehicleDto;
import com.example.package_delivery_system.data.dtos.vehicleDtos.VehicleResponseDto;


public interface VehicleService {

     VehicleResponseDto createVehicle(CreateVehicleDto createVehicleDto);
}
