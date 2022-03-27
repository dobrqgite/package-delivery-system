package com.example.package_delivery_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaRepositories
@SpringBootApplication
@EnableWebMvc
public class PackageDeliverySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PackageDeliverySystemApplication.class, args);
    }

}
