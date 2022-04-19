package com.example.package_delivery_system.data.repositories;


import com.example.package_delivery_system.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAll();

    UserEntity findByEmail(String email);

    boolean existsByUsernameOrEmail(String username, String email);

    boolean existsByPhone(String phone);
}
