package com.example.package_delivery_system.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "offices")
public class Office extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
