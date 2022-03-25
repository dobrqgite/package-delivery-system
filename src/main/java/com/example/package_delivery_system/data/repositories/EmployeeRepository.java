package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.Driver;
import com.example.package_delivery_system.data.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
