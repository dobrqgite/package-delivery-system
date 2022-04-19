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
    private String description;

    private BigDecimal price;

    @NotNull
    private BigDecimal weight;

    @NotNull
    private BigDecimal height;

    @NotNull
    private BigDecimal width;

    @NotNull
    private BigDecimal length;

    @NotNull
    private String recipientEmail;

    @NotNull
    private String payingUser;

    @NotNull
    private String paymentMethod;

}
