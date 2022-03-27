package com.example.package_delivery_system.data.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "drivers")
public class Driver extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToMany
    @JoinTable(
            name = "drivers_vehicles",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private Set<Vehicle> vehicles;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
