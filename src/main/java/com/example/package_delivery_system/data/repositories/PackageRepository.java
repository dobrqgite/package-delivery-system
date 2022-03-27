package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {

    List<Package> findAll();

}
