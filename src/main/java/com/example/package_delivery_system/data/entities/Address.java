package com.example.package_delivery_system.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    @Column(name = "country")
    private String country;

    @Column(name = "town")
    private String town;


    @Column(name = "full_address")
    private String fullAddress;


    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne(mappedBy = "address")
    private Office office;

    @OneToMany(mappedBy = "addressFrom")
    private List<Package> packagesToSend;


    @OneToMany(mappedBy = "addressTo")
    private List<Package> packagesToReceive;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public List<Package> getPackagesToSend() {
        return packagesToSend;
    }

    public void setPackagesToSend(List<Package> packagesToSend) {
        this.packagesToSend = packagesToSend;
    }

    public List<Package> getPackagesToReceive() {
        return packagesToReceive;
    }

    public void setPackagesToReceive(List<Package> packagesToReceive) {
        this.packagesToReceive = packagesToReceive;
    }
}
