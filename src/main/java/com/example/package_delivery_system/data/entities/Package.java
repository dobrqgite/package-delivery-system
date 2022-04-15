package com.example.package_delivery_system.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "packages")
@Getter
@Setter
@NoArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id", nullable = false)
    private UserEntity receiver;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;

    @Column(name = "content")
    private String content;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "is_delivered", columnDefinition = "tinyint(1) default 0")
    private boolean isDelivered;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "delivery_tax")
    private BigDecimal deliveryTax;

    @OneToOne(mappedBy = "packageInfo")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn (name = "payer_id")
    private UserEntity payingUser;

    @Column(name = "payment_method")
    private String paymentMethod;
}