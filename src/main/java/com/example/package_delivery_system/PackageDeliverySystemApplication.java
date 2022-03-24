package com.example.package_delivery_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class PackageDeliverySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PackageDeliverySystemApplication.class, args);
    }

}