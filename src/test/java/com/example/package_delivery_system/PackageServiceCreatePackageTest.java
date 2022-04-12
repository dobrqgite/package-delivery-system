package com.example.package_delivery_system;

import com.example.package_delivery_system.data.repositories.PackageRepository;
import com.example.package_delivery_system.services.PackageService;
import com.example.package_delivery_system.services.impl.PackageServiceImpl;
import com.example.package_delivery_system.services.impl.UserServiceImpl;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PackageServiceCreatePackageTest {

    @Autowired
    private PackageService packageService;

    @TestConfiguration
   static class PackageServiceCreatePackageTestConfiguration {

        @Autowired
        private UserServiceImpl userService;
        @Autowired
        private PackageRepository packageRepository;
        @Autowired
        private ModelMapper modelMapper;

        @Bean
        public PackageService packageService() {
            return new PackageServiceImpl(userService, packageRepository, modelMapper);
        }

    }

}
