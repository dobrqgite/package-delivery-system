package com.example.package_delivery_system.services.impl;

import com.example.package_delivery_system.data.dtos.employeeDtos.CreateDriverDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.DriverResponseDto;
import com.example.package_delivery_system.data.entities.UserEntity;
import com.example.package_delivery_system.data.repositories.RoleRepository;
import com.example.package_delivery_system.data.repositories.UserRepository;
import com.example.package_delivery_system.exceptions.BadRequestException;
import com.example.package_delivery_system.services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {
    public static final String DRIVER_ALREADY_EXISTS = "Driver with name %s already exists!%n";

    public static final String PASSWORDS_DO_NOT_MATCH = "Passwords do not match!";

    public static final String PHONE_ALREADY_EXISTS = "The phone you entered already exists!";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public DriverResponseDto registerDriver(CreateDriverDto driverDto) {
        if (this.userRepository.existsByUsernameOrEmail(driverDto.getUsername(), driverDto.getEmail())){
            throw new BadRequestException(String.format(DRIVER_ALREADY_EXISTS, driverDto.getUsername()));
        }

        if (!driverDto.getPassword().equals(driverDto.getConfirmPassword())){
            throw new BadRequestException(PASSWORDS_DO_NOT_MATCH);
        }

        if (this.userRepository.existsByPhone(driverDto.getPhone())) {
            throw new BadRequestException(PHONE_ALREADY_EXISTS);
        }

        UserEntity driver = this.modelMapper.map(driverDto, UserEntity.class);
        driver.setRoles(Set.of(this.roleRepository.getRoleByAuthority("DRIVER").get()));
        this.userRepository.save(driver);

        return this.modelMapper.map(driver, DriverResponseDto.class);
    }
}
