package com.example.package_delivery_system.data.dtos.vehicleDtos;

import com.example.package_delivery_system.data.enums.VehicleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateVehicleDto {

    //test with string first, then with VehicleType
    private String type;

    private String brand;

    private String registrationNumber;
}