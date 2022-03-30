package com.example.package_delivery_system.services.impl;

import com.example.package_delivery_system.exceptions.UserExceptions;
import com.example.package_delivery_system.data.dtos.UserLoginDto;
import com.example.package_delivery_system.data.dtos.UserRegisterDto;
import com.example.package_delivery_system.data.dtos.UserResponseDto;
import com.example.package_delivery_system.data.entities.User;
import com.example.package_delivery_system.data.repositories.UserRepository;
import com.example.package_delivery_system.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public boolean register(UserRegisterDto userRegisterDto) {

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
            return false;
        }

        if (!password.equals(confirmPassword)) {
            System.out.println(UserExceptions.PASSWORDS_DO_NOT_MATCH);
            return false;
        }

        User user = new User();
        user.setFullName(firstName + " " + lastName);
        user.setUsername(username);
        user.setPhone(phone);
        user.setUCN(UCN);
        user.setEmail(eMail);
        user.setPassword(passwordEncoder.encode(password));

        this.userRepository.save(user);

        this.modelMapper.map(user, UserResponseDto.class);

        return true;
    }

    @Override
    public boolean login(UserLoginDto userLoginDto) {
        return false;
    }
}
