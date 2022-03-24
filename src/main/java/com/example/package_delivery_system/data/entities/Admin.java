package com.example.package_delivery_system.data.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
