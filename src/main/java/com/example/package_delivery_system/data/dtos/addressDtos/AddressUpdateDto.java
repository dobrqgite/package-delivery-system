package com.example.package_delivery_system.data.dtos.addressDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddressUpdateDto {

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private String fullAddress;
}