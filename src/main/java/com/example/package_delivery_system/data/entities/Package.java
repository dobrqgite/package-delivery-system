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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "address_to", nullable = false)
    private Address addressTo;

    @ManyToOne
    @JoinColumn(name = "address_from", nullable = false)
    private Address addressFrom;

    @Column(name = "content")
    private String content;

    @Column(name = "is_delivered", columnDefinition = "tinyint(1) default 0")
    private boolean isDelivered;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "delivery_tax")
    private BigDecimal deliveryTax;

    @OneToOne(mappedBy = "packageId")
    private Transaction transaction;
}
