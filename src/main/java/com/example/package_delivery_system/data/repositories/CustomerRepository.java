package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.Agent;
import com.example.package_delivery_system.data.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
