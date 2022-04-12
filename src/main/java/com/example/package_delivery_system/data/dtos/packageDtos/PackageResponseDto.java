package com.example.package_delivery_system.data.dtos.packageDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageResponseDto {

    private Long id;

    private String description;

    private String price;

    private BigDecimal weight;

    private BigDecimal height;

    private BigDecimal width;

    private BigDecimal length;

    private String recipientEmail;

}
