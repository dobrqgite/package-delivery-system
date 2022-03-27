package com.example.package_delivery_system.data.entities;

import com.example.package_delivery_system.data.enums.VehicleType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseEntity {

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Column(name = "reg_number")
    private String registrationNumber;

    @ManyToMany(mappedBy = "vehicles")
    private Set<Driver> drivers;

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
