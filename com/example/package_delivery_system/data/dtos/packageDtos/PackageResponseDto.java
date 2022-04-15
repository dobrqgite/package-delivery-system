package com.example.package_delivery_system.data.dtos.packageDtos;

import com.example.package_delivery_system.data.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String payingUser;

    private String paymentMethod;

}
