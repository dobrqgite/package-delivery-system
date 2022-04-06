package com.example.package_delivery_system.services.impl;

import com.example.package_delivery_system.data.dtos.UserLoginDto;
import com.example.package_delivery_system.data.dtos.UserRegisterDto;
import com.example.package_delivery_system.data.entities.Role;
import com.example.package_delivery_system.data.entities.UserEntity;
import com.example.package_delivery_system.data.repositories.RoleRepository;
import com.example.package_delivery_system.data.repositories.UserRepository;
import com.example.package_delivery_system.exceptions.UserExceptions;
import com.example.package_delivery_system.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void register(UserRegisterDto userRegisterDto) {

        Optional<Role> customerRole = this.roleRepository.getRoleByAuthority("CUSTOMER");
        if (customerRole.isEmpty()) {
            Role role = new Role("CUSTOMER");
            this.roleRepository.save(role);
        }
        String username = userRegisterDto.getUsername();
        String firstName = userRegisterDto.getFirstName();
        String lastName = userRegisterDto.getLastName();
        String UCN = userRegisterDto.getUCN();
        String phone = userRegisterDto.getPhone();
        String eMail = userRegisterDto.getEmail();
        String password = userRegisterDto.getPassword();
        String confirmPassword = userRegisterDto.getConfirmPassword();

        if (this.userRepository.existsByUsernameOrEmail(username, eMail)) {
            System.out.printf(UserExceptions.USER_ALREADY_EXISTS, username);
        } else if (!password.equals(confirmPassword)) {
            System.out.println(UserExceptions.PASSWORDS_DO_NOT_MATCH);
        } else if (this.userRepository.existsByPhone(phone)) {
            System.out.println(UserExceptions.PHONE_ALREADY_EXISTS);
        } else {
            UserEntity user = new UserEntity();
            user.setFullName(firstName + " " + lastName);
            user.setUsername(username);
            user.setPhone(phone);
            user.setUCN(UCN);
            user.setEmail(eMail);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(Set.of(roleRepository.getRoleByAuthority("CUSTOMER").get()));
            this.userRepository.save(user);
        }
    }

    @Override
    public boolean login(UserLoginDto userLoginDto) {
        return false;
    }



    // implemented by UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
