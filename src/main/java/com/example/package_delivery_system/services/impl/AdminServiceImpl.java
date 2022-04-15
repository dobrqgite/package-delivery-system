package com.example.package_delivery_system.services.impl;

import com.example.package_delivery_system.data.dtos.employeeDtos.EmployeeRegisterDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.EmployeeResponseDto;
import com.example.package_delivery_system.data.dtos.userDtos.GetUserInfoDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserBanDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserResponseDto;
import com.example.package_delivery_system.data.entities.Address;
import com.example.package_delivery_system.data.entities.UserEntity;
import com.example.package_delivery_system.data.repositories.RoleRepository;
import com.example.package_delivery_system.data.repositories.UserRepository;
import com.example.package_delivery_system.exceptions.BadRequestException;
import com.example.package_delivery_system.services.api.AddressService;
import com.example.package_delivery_system.services.api.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    public static final String DRIVER_ALREADY_EXISTS = "Driver with name %s already exists!%n";

    public static final String PASSWORDS_DO_NOT_MATCH = "Passwords do not match!";

    public static final String PHONE_ALREADY_EXISTS = "The phone you entered already exists!";

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AddressService addressService;

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

        if (this.userRepository.existsByUsernameOrEmail(employeeRegisterDto.getUsername(), employeeRegisterDto.getEmail())) {
            throw new BadRequestException(String.format(DRIVER_ALREADY_EXISTS, employeeRegisterDto.getUsername()));
        }

        if (!employeeRegisterDto.getPassword().equals(employeeRegisterDto.getConfirmPassword())) {
            throw new BadRequestException(PASSWORDS_DO_NOT_MATCH);
        }

        if (this.userRepository.existsByPhone(employeeRegisterDto.getPhone())) {
            throw new BadRequestException(PHONE_ALREADY_EXISTS);
        }
        Address agentAddress = this.addressService.createEmployeeAddress(employeeRegisterDto);

        UserEntity employee = this.modelMapper.map(employeeRegisterDto, UserEntity.class);
        employee.setAddress(agentAddress);
        employee.setFullName(fullName);
        employee.setPassword(passwordEncoder.encode(password));
        if (employeeRegisterDto.getRole().equals("AGENT")) {
            employee.setRoles(Set.of(this.roleRepository.getRoleByAuthority("AGENT").get()));
        } else {
            employee.setRoles(Set.of(this.roleRepository.getRoleByAuthority("DRIVER").get()));
        }
        this.userRepository.save(employee);

        return this.modelMapper.map(employee, EmployeeResponseDto.class);
    }

    @Override
    public UserResponseDto banUser(UserBanDto userBanDto) {
        UserEntity userToBan = this.userRepository.findByEmail(userBanDto.getEmail());
        userToBan.setActive(false);

        this.userRepository.save(userToBan);
        return this.modelMapper.map(userToBan, UserResponseDto.class);
    }

    @Override
    public List<GetUserInfoDto> getGetCustomerInfo(String userRole) {
        return this.userRepository.findAll()
                .stream()
                .filter(user -> user.getRoles().stream().anyMatch(role -> role.getAuthority().equals(userRole)))
                .map(user -> this.modelMapper.map(user, GetUserInfoDto.class))
                .collect(Collectors.toList());
    }
}