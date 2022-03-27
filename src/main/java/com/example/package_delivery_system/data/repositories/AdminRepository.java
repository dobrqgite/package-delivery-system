package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.Admin;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    List<Admin> findAll();

    Admin findAdminById(Long id);
}
