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
    public AddressDto createUserAddress(UserRegisterDto userAddressFromRegisterDto) {

        String country = userAddressFromRegisterDto.getCountry();
        String city = userAddressFromRegisterDto.getCity();
        String fullAddress = userAddressFromRegisterDto.getFullAddress();

        Address address = new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setFullAddress(fullAddress);

        return this.modelMapper.map(this.addressRepository.save(address), AddressDto.class);
    }

    Address findById(Long id){
        return this.addressRepository.findById(id).orElse(null);
    }
}
