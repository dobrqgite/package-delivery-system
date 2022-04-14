package com.example.package_delivery_system.data.dtos.transactionDtos;

import com.example.package_delivery_system.data.entities.Package;
import com.example.package_delivery_system.data.entities.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {

    private Long id;

    private UserEntity payerId;

    private String paymentMethod;

    private BigDecimal amount;

    private Package packageId;

}
