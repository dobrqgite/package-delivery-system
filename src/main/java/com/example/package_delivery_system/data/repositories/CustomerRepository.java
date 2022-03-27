package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAll();

    Customer findCustomerById(Long id);

    Customer findCustomerByPhoneNumber(String phoneNumber);

    Customer findCustomerByAddressId(Long addressId);
}
