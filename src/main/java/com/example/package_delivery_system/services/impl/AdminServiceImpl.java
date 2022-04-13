package com.example.package_delivery_system.services.impl;

import com.example.package_delivery_system.data.dtos.employeeDtos.DriverRegisterDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.DriverResponseDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.agentDtos.AgentRegisterDto;
import com.example.package_delivery_system.data.dtos.employeeDtos.agentDtos.AgentResponseDto;
import com.example.package_delivery_system.data.entities.Address;
import com.example.package_delivery_system.data.entities.UserEntity;
import com.example.package_delivery_system.data.repositories.RoleRepository;
import com.example.package_delivery_system.data.repositories.UserRepository;
import com.example.package_delivery_system.exceptions.BadRequestException;
import com.example.package_delivery_system.services.AddressService;
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
    private final AddressService addressService;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository, AddressService addressService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.addressService = addressService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public DriverResponseDto registerDriver(DriverRegisterDto driverDto) {

        String fullName = driverDto.getDriverFirstName() + " " + driverDto.getDriverLastName();

        if (this.userRepository.existsByUsernameOrEmail(driverDto.getDriverUsername(), driverDto.getDriverEmail())){
            throw new BadRequestException(String.format(DRIVER_ALREADY_EXISTS, driverDto.getDriverUsername()));
        }

        if (!driverDto.getDriverPassword().equals(driverDto.getDriverConfirmPassword())){
            throw new BadRequestException(PASSWORDS_DO_NOT_MATCH);
        }

        if (this.userRepository.existsByPhone(driverDto.getDriverPhone())) {
            throw new BadRequestException(PHONE_ALREADY_EXISTS);
        }
        Address driverAddress = this.addressService.createUserAddress(driverDto);

        UserEntity driver = this.modelMapper.map(driverDto, UserEntity.class);
        driver.setAddress(driverAddress);
        driver.setFullName(fullName);
        driver.setRoles(Set.of(this.roleRepository.getRoleByAuthority("DRIVER").get()));
        this.userRepository.save(driver);

        return this.modelMapper.map(driver, DriverResponseDto.class);
    }

    @Override
    public AgentResponseDto registerAgent(AgentRegisterDto agentDto) {

        String fullName = agentDto.getAgentFirstName() + " " + agentDto.getAgentLastName();

        if (this.userRepository.existsByUsernameOrEmail(agentDto.getAgentUsername(), agentDto.getAgentEmail())){
            throw new BadRequestException(String.format(DRIVER_ALREADY_EXISTS, agentDto.getAgentUsername()));
        }

        if (!agentDto.getAgentPassword().equals(agentDto.getAgentConfirmPassword())){
            throw new BadRequestException(PASSWORDS_DO_NOT_MATCH);
        }

        if (this.userRepository.existsByPhone(agentDto.getAgentPhone())) {
            throw new BadRequestException(PHONE_ALREADY_EXISTS);
        }
        Address agentAddress = this.addressService.createUserAddress(agentDto);

        UserEntity agent = this.modelMapper.map(agentDto, UserEntity.class);
        agent.setAddress(agentAddress);
        agent.setFullName(fullName);
        agent.setRoles(Set.of(this.roleRepository.getRoleByAuthority("AGENT").get()));
        this.userRepository.save(agent);

        return this.modelMapper.map(agent, AgentResponseDto.class);
    }

}
