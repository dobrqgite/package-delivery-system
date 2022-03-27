package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.User;
import com.example.package_delivery_system.data.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAll();

    Vehicle findVehicleById(Long vehicleId);

}
