package com.example.package_delivery_system.services.impl;

import com.example.package_delivery_system.data.dtos.vehicleDtos.VehicleDto;
import com.example.package_delivery_system.data.entities.Vehicle;
import com.example.package_delivery_system.data.enums.VehicleType;
import com.example.package_delivery_system.data.repositories.VehicleRepository;
import com.example.package_delivery_system.exceptions.BadRequestException;
import com.example.package_delivery_system.services.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class VehicleServiceImpl implements VehicleService {
    //TODO:FIX PATH TO OPEN FROM SRC
    private final Path path = Path.of("/Users/dimitarbalimezov/Downloads/PDS/package-delivery-system/src/main/resources/files/CatBrandReg.txt");

    private final VehicleRepository vehicleRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void seedVehicles() throws IOException {
        String[] vehicles = Files.readString(path).split("\n");
        for (String vehicle : vehicles) {
            try {
                VehicleDto dto = new VehicleDto();
                dto.setType(VehicleType.valueOf(vehicle.split(", ")[0]));
                dto.setBrand(vehicle.split(", ")[1]);
                dto.setRegistrationNumber(vehicle.split(", ")[2]);
                if (this.vehicleRepository.existsByRegistrationNumber(vehicle.split(", ")[2])){
                    throw new BadRequestException("Vehicle is already registered");
                }
                this.vehicleRepository.save(this.modelMapper.map(dto, Vehicle.class));
            } catch (BadRequestException e){
                e.printStackTrace();
            }
        }
    }

}
