package com.example.package_delivery_system.data.dtos.vehicleDtos;

import com.example.package_delivery_system.data.enums.VehicleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VehicleDto {

    private VehicleType type;

    private String brand;

    private String registrationNumber;


}
