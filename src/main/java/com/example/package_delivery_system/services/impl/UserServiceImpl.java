package com.example.package_delivery_system.services.impl;

import com.example.package_delivery_system.data.dtos.UserRegisterDto;
import com.example.package_delivery_system.data.entities.User;
import com.example.package_delivery_system.data.repositories.UserRepository;
import com.example.package_delivery_system.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean register(UserRegisterDto userRegisterDto) {
        if (this.userRepository.existsByUsernameOrEmail(
                userRegisterDto.getUsername(),
                userRegisterDto.getEmail())) {
            return false;
        }

        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return false;
        }

        var user = this.modelMapper.map(userRegisterDto, User.class);

         this.userRepository.save(user);

        return true;
    }
}
