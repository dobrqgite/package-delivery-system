package com.example.package_delivery_system.data.dtos.packageDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreatePackageDto {

    @NotNull
    private String content;

    private BigDecimal weight;
    // x, y, z / measurements
    private BigDecimal height;

    private BigDecimal width;

    private BigDecimal length;

    /* content
    weight
    height
    width
    length
    sender - get from current session
    receiver
    add method to validate package size DON'T OVERENGENEER
     */

}
