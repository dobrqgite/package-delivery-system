package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleById(Long id);

    Optional<Role> getRoleByAuthority(String authority);
}
