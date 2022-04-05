package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {

    List<Office> findAll();

    Office findOfficeById(Long id);

    Office findOfficeByName(String name);

    Office findOfficeByAddressId(Long addressId);
}
