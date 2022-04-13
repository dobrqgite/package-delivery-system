package com.example.package_delivery_system.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TransactionAmount {

    private BigDecimal packagePrice;
    @NotNull
    private BigDecimal deliveryPrice;
}
