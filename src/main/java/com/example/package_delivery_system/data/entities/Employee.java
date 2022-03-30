package com.example.package_delivery_system.data.entities;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "employee")
    private Admin admin;

    @OneToOne(mappedBy = "employee")
    private Agent agent;

    @OneToOne(mappedBy = "employee")
    private Driver driver;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
