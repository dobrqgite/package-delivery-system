package com.example.package_delivery_system.services.impl;

import com.example.package_delivery_system.data.dtos.user.UserRegisterDto;
import com.example.package_delivery_system.data.dtos.user.UserResponseDto;
import com.example.package_delivery_system.data.dtos.user.UserUpdateDto;
import com.example.package_delivery_system.data.entities.Address;
import com.example.package_delivery_system.data.entities.Role;
import com.example.package_delivery_system.data.entities.UserEntity;
import com.example.package_delivery_system.data.repositories.AddressRepository;
import com.example.package_delivery_system.data.repositories.RoleRepository;
import com.example.package_delivery_system.data.repositories.UserRepository;
import com.example.package_delivery_system.exceptions.BadRequestException;
import com.example.package_delivery_system.services.AddressService;
import com.example.package_delivery_system.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    public static final String USER_ALREADY_EXISTS = "User with name %s already exists!%n";

    public static final String PASSWORDS_DO_NOT_MATCH = "Passwords do not match!";

    public static final String PHONE_ALREADY_EXISTS = "The phone you entered already exists!";


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AddressService addressService;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository, AddressService addressService, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.addressService = addressService;
        this.addressRepository = addressRepository;
        this.modelMapper = new ModelMapper();
    }

    //make method return a response dto
    @Override
    @Transactional
    public UserResponseDto register(UserRegisterDto userRegisterDto) {

        if (this.roleRepository.count() == 0) {
            this.seedRoles();
        }
        String username = userRegisterDto.getUsername();
        String fullName = userRegisterDto.getFirstName() + " " + userRegisterDto.getLastName();
        String UCN = userRegisterDto.getUCN();
        String phone = userRegisterDto.getPhone();
        String eMail = userRegisterDto.getEmail();
        String password = userRegisterDto.getPassword();
        String confirmPassword = userRegisterDto.getConfirmPassword();

        if (this.userRepository.existsByUsernameOrEmail(username, eMail)) {
            throw new BadRequestException(String.format(USER_ALREADY_EXISTS, username));
        }
        if (!password.equals(confirmPassword)) {
            throw new BadRequestException(PASSWORDS_DO_NOT_MATCH);
        }
        if (this.userRepository.existsByPhone(phone)) {
            throw new BadRequestException(PHONE_ALREADY_EXISTS);
        }
        Address userAddress = this.addressService.createUserAddress(userRegisterDto);


        UserEntity user = new UserEntity();
        user.setFullName(fullName);
        user.setUsername(username);
        user.setPhone(phone);
        user.setUCN(UCN);
        user.setAddress(userAddress);
        user.setEmail(eMail);
        user.setPassword(passwordEncoder.encode(password));
        if (this.userRepository.count() == 0) {
            user.setRoles(Set.of(this.roleRepository.getRoleByAuthority("ADMIN").get()));
        } else {
            user.setRoles(Set.of(this.roleRepository.getRoleByAuthority("CUSTOMER").get()));
        }
        this.userRepository.save(user);

        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto editCredentials(UserUpdateDto userUpdateDto) {
        return null;
    }

    @Override
    public void seedRoles() {
        Role adminRole = new Role("ADMIN");
        Role customerRole = new Role("CUSTOMER");
        Role agentRole = new Role("AGENT");
        Role driverRole = new Role("DRIVER");

        Set<Role> roles = new HashSet<>();

        roles.add(adminRole);
        roles.add(customerRole);
        roles.add(agentRole);
        roles.add(driverRole);

        this.roleRepository.saveAll(roles);
    }

    // implemented by UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserEntity user = this.userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return user;
    }
}
