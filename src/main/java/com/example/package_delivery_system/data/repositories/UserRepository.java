package com.example.package_delivery_system.data.repositories;


import com.example.package_delivery_system.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAll();

    UserEntity findAllById(Long id);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    UserEntity findUserByFullName(String fullName);

    UserEntity findByUsernameAndEmail(String username, String email);

    boolean existsByUsernameOrEmail(String username, String email);

    boolean existsByPhone(String phone);
}
