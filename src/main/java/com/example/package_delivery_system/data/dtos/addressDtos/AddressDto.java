package com.example.package_delivery_system.data.dtos.addressDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {

    private String country;

    private String city;

    private String fullAddress;
}
