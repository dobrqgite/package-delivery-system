package com.example.package_delivery_system.data.dtos.packageDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreatePackageDto {

    //what is it?
    private String content;

    private BigDecimal weight;
    // x, y, z / measurements
    private BigDecimal height;

    private BigDecimal width;

    private BigDecimal length;

    private BigDecimal tax;
}
