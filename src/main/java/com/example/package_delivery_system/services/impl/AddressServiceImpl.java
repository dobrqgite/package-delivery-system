package com.example.package_delivery_system.services.impl;

import com.example.package_delivery_system.data.dtos.addressDtos.AddressDto;
import com.example.package_delivery_system.data.dtos.user.UserRegisterDto;
import com.example.package_delivery_system.data.entities.Address;
import com.example.package_delivery_system.data.repositories.AddressRepository;
import com.example.package_delivery_system.services.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional
    public Address createUserAddress(UserRegisterDto userAddressFromRegisterDto) {

        String country = userAddressFromRegisterDto.getCountry();
        String city = userAddressFromRegisterDto.getCity();
        String fullAddress = userAddressFromRegisterDto.getFullAddress();

        Address address = this.modelMapper.map(userAddressFromRegisterDto, Address.class);

        return this.addressRepository.save(address);
    }

    Address findById(Long id){
        return this.addressRepository.findById(id).orElse(null);
    }
}
