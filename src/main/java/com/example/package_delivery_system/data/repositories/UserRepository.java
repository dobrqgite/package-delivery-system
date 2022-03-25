package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.Transaction;
import com.example.package_delivery_system.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> findAllUsers();

    User findUserByEmail(String email);

    User findUserByFullName(String fullName);
}
