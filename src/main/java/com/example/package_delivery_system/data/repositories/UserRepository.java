package com.example.package_delivery_system.data.repositories;


import com.example.package_delivery_system.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User findAllById(Long id);

    User findAllByEmail(String email);

    User findUserByFullName(String fullName);

    User findByUsernameAndEmail(String username, String email);

    boolean existsByUsernameOrEmail(String username, String email);
}
