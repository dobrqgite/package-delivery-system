package com.example.package_delivery_system.services.impl;

import com.example.package_delivery_system.data.dtos.vehicleDtos.CreateVehicleDto;
import com.example.package_delivery_system.data.dtos.vehicleDtos.VehicleResponseDto;
import com.example.package_delivery_system.data.repositories.VehicleRepository;
import com.example.package_delivery_system.services.api.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final ModelMapper modelMapper;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public VehicleResponseDto addVehicle(CreateVehicleDto createVehicleDto) {
        return null;
    }
}
