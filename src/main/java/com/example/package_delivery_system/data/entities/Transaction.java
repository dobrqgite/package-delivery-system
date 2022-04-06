package com.example.package_delivery_system.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payer_id", nullable = false)
    private UserEntity payerId;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "amount")
    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "package_id")
    private Package packageId;
}
