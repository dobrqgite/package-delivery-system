package com.example.package_delivery_system.data.dtos.vehicleDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetVehicleInfoDto {

    private String category;

    private String brand;

    private String registrationNumber;

}