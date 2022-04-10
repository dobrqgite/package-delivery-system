package com.example.package_delivery_system.data.dtos.packageDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SendPackageDto {


    private Long id;
    private String receivingUser;
    private BigDecimal price;

}
