package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.Customer;
import com.example.package_delivery_system.data.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
