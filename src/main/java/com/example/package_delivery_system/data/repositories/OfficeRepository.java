package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.Employee;
import com.example.package_delivery_system.data.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
}
