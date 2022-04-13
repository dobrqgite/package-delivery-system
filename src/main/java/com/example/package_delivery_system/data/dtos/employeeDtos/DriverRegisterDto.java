package com.example.package_delivery_system.data.dtos.employeeDtos;

import com.example.package_delivery_system.data.dtos.vehicleDtos.VehicleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class DriverRegisterDto {

    @NotNull
    private String driverUsername;

    @NotNull
    private String driverFirstName;

    @NotNull
    private String driverLastName;

    @NotNull
    private String driverPhone;

    @NotNull
    private String driverUCN;

    @NotNull
    private String driverCountry;

    @NotNull
    private String driverCity;

    @NotNull
    private String driverFullAddress;

    @Email
    @NotNull
    private String driverEmail;

    @NotNull
    private String driverPassword;

    @NotNull
    private String driverConfirmPassword;

    private VehicleDto vehicleInUse;
}
