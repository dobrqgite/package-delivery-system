package com.example.package_delivery_system.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "town")
    private String town;

    @Column(name = "full_address")
    private String fullAddress;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    private Office office;

    @OneToMany(mappedBy = "addressFrom")
    private List<Package> packagesToSend;


    @OneToMany(mappedBy = "addressTo")
    private List<Package> packagesToReceive;
}
