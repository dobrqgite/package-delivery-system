package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {

    List<Package> findAll();

    Optional<Package> findById(Long packageId);

    List<Package> findAllBySender(Long senderId);
}
