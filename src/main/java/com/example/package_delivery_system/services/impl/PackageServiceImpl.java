package com.example.package_delivery_system.services.impl;

import com.example.package_delivery_system.data.dtos.packageDtos.CreatePackageDto;
import com.example.package_delivery_system.data.dtos.packageDtos.PackageResponseDto;
import com.example.package_delivery_system.data.entities.Package;
import com.example.package_delivery_system.data.repositories.PackageRepository;
import com.example.package_delivery_system.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PackageServiceImpl implements PackageService {

    private final UserServiceImpl userService;
    private final PackageRepository packageRepository;

    @Autowired
    public PackageServiceImpl(UserServiceImpl userService, PackageRepository packageRepository) {
        this.userService = userService;
        this.packageRepository = packageRepository;
    }


    @Override
    public PackageResponseDto addPackage(CreatePackageDto createPackageDto) {

        String content = createPackageDto.getContent();
        BigDecimal weight = createPackageDto.getWeight();
        BigDecimal height = createPackageDto.getHeight();
        BigDecimal width = createPackageDto.getWidth();
        BigDecimal length = createPackageDto.getLength();

        Package packageToAdd = new Package();
        packageToAdd.setContent(content);
        packageToAdd.setDeliveryTax(calculateTax(weight, height, length, width));

        this.packageRepository.save(packageToAdd);

        PackageResponseDto packageResponseDto = new PackageResponseDto();

        return packageResponseDto;
    }

    private BigDecimal calculateTax(BigDecimal weight, BigDecimal height, BigDecimal length, BigDecimal width) {

        double calculatedTax = 0.1 * (weight.doubleValue() + length.doubleValue() +
                width.doubleValue() + height.doubleValue());

        double weightMultiplier = calculateWeightMultiplier(weight.doubleValue());

        double sizeMultiplier = calculateSizeMultiplier(height.doubleValue(), length.doubleValue(), width.doubleValue());

        double tax = calculatedTax * weightMultiplier * sizeMultiplier;

        return BigDecimal.valueOf(tax);
    }

    private double calculateWeightMultiplier(double weight) {
        if (weight <= 1) {
            return 1.1;
        }
        if (weight > 1 && weight <= 10) {
            return 1.15;
        }
        if (weight > 10 && weight <= 30) {
            return 1.20;
        }
        if (weight > 30 && weight <= 60) {
            return 1.30;
        }
        return 1.7;
    }

    private double calculateSizeMultiplier(double height, double width, double length) {

        if (height <= 61 && width <= 44 && length <= 37) {
            return 1.1;
        }
        if (height >= 61 && width >= 44 && length >= 37
                && height <= 90 && width <= 90 && length <= 90) {
            return 1.15;
        }
        if (height > 90 && width > 90 && length > 90
                && height <= 160 && width <= 160 && length <= 160) {
            return 1.4;
        }
        return 2.1;
    }
}
