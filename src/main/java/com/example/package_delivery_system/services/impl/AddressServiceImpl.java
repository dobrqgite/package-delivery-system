package com.example.package_delivery_system.services.impl;

import com.example.package_delivery_system.data.dtos.employeeDtos.DriverRegisterDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.agentDtos.AgentRegisterDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserRegisterDto;
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

    @Override
    public Address createUserAddress(DriverRegisterDto driverAddressFromRegisterDto) {
        String country = driverAddressFromRegisterDto.getDriverCountry();
        String city = driverAddressFromRegisterDto.getDriverCity();
        String fullAddress = driverAddressFromRegisterDto.getDriverFullAddress();

        Address address = this.modelMapper.map(driverAddressFromRegisterDto, Address.class);

        return this.addressRepository.save(address);
    }

    @Override
    public Address createUserAddress(AgentRegisterDto agentAddressFromRegisterDto) {
        String country = agentAddressFromRegisterDto.getAgentCountry();
        String city = agentAddressFromRegisterDto.getAgentCity();
        String fullAddress = agentAddressFromRegisterDto.getAgentFullAddress();

        Address address = this.modelMapper.map(agentAddressFromRegisterDto, Address.class);

        return this.addressRepository.save(address);
    }

    Address findById(Long id){
        return this.addressRepository.findById(id).orElse(null);
    }
}
