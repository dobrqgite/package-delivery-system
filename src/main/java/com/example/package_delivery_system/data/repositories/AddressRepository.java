package com.example.package_delivery_system.data.repositories;

import com.example.package_delivery_system.data.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAll();

    Address findAddressById(Long id);

    Address findAddressByCountry(String country);

    Address findAddressByTown(String town);

    Address findAddressByFullAddress(String fullAddress);

}
