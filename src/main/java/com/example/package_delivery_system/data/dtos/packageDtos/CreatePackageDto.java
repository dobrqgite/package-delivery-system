package com.example.package_delivery_system.data.dtos.packageDtos;

import com.example.package_delivery_system.data.entities.UserEntity;
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
    private String description;

    private BigDecimal weight;
    // x, y, z / measurements
    private BigDecimal height;

    private BigDecimal width;

    private BigDecimal length;

    private String recipientEmail;

}
