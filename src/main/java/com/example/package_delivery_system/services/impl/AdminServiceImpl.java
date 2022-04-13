package com.example.package_delivery_system.services.impl;

import com.example.package_delivery_system.data.dtos.employeeDtos.EmployeeRegisterDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.EmployeeResponseDto;
import com.example.package_delivery_system.data.entities.Address;
import com.example.package_delivery_system.data.entities.UserEntity;
import com.example.package_delivery_system.data.repositories.RoleRepository;
import com.example.package_delivery_system.data.repositories.UserRepository;
import com.example.package_delivery_system.exceptions.BadRequestException;
import com.example.package_delivery_system.services.AddressService;
import com.example.package_delivery_system.services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final AddressService addressService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                            AddressService addressService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public EmployeeResponseDto registerEmployee(EmployeeRegisterDto employeeRegisterDto) {

        String fullName = employeeRegisterDto.getFirstName() + " " + employeeRegisterDto.getLastName();
        String password = employeeRegisterDto.getPassword();

        if (this.userRepository.existsByUsernameOrEmail(employeeRegisterDto.getUsername(), employeeRegisterDto.getEmail())){
            throw new BadRequestException(String.format(DRIVER_ALREADY_EXISTS, employeeRegisterDto.getUsername()));
        }

        if (!employeeRegisterDto.getPassword().equals(employeeRegisterDto.getConfirmPassword())){
            throw new BadRequestException(PASSWORDS_DO_NOT_MATCH);
        }

        if (this.userRepository.existsByPhone(employeeRegisterDto.getPhone())) {
            throw new BadRequestException(PHONE_ALREADY_EXISTS);
        }
        Address agentAddress = this.addressService.createUserAddress(employeeRegisterDto);

        UserEntity employee = this.modelMapper.map(employeeRegisterDto, UserEntity.class);
        employee.setAddress(agentAddress);
        employee.setFullName(fullName);
        employee.setPassword(passwordEncoder.encode(password));
        if (employeeRegisterDto.getRole().equals("AGENT")) {
            employee.setRoles(Set.of(this.roleRepository.getRoleByAuthority("AGENT").get()));
        }else{
            employee.setRoles(Set.of(this.roleRepository.getRoleByAuthority("DRIVER").get()));
        }
        this.userRepository.save(employee);

        return this.modelMapper.map(employee, EmployeeResponseDto.class);
    }
}
