package com.example.package_delivery_system;

import com.example.package_delivery_system.data.entities.User;
import com.example.package_delivery_system.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    UserRepository userRepository;

    @Autowired
    public CommandLineRunnerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        try {
            System.out.println(this.userRepository.findUserByFullName("asd"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
