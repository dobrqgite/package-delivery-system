package com.example.package_delivery_system.exceptions.impl;

import com.example.package_delivery_system.data.dtos.packageDtos.CreatePackageDto;
import com.example.package_delivery_system.data.dtos.packageDtos.PackageResponseDto;
import com.example.package_delivery_system.data.entities.Package;
import com.example.package_delivery_system.data.entities.Transaction;
import com.example.package_delivery_system.data.entities.UserEntity;
import com.example.package_delivery_system.data.repositories.PackageRepository;
import com.example.package_delivery_system.data.repositories.TransactionRepository;
import com.example.package_delivery_system.services.api.PackageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PackageServiceImpl implements PackageService {

    private final UserServiceImpl userService;
    private final PackageRepository packageRepository;
    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;

    @Autowired
    public PackageServiceImpl(UserServiceImpl userService, PackageRepository packageRepository, ModelMapper modelMapper, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.packageRepository = packageRepository;
        this.modelMapper = modelMapper;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public PackageResponseDto addPackage(CreatePackageDto createPackageDto, String currentlyLoggedInUserEmail) {

        String content = createPackageDto.getDescription();
        BigDecimal weight = createPackageDto.getWeight();
        BigDecimal height = createPackageDto.getHeight();
        BigDecimal width = createPackageDto.getWidth();
        BigDecimal length = createPackageDto.getLength();
        BigDecimal price = createPackageDto.getPrice();
        UserEntity sender = (UserEntity) userService.loadUserByUsername(currentlyLoggedInUserEmail);
        UserEntity receiver = (UserEntity) userService.loadUserByUsername(createPackageDto.getRecipientEmail());
        UserEntity payingUser = (UserEntity) userService.loadUserByUsername(createPackageDto.getPayingUser());
        String paymentMethod = createPackageDto.getPaymentMethod();

        Package packageToAdd = new Package();

        packageToAdd.setContent(content);
        packageToAdd.setSender(sender);
        packageToAdd.setReceiver(receiver);
        packageToAdd.setWeight(weight);
        packageToAdd.setPrice(price);
        packageToAdd.setDelivered(false);
        packageToAdd.setDeliveryTax(calculateTax(weight, height, length, width));

        BigDecimal dueAmount = packageToAdd.getPrice().add(packageToAdd.getDeliveryTax());

        Transaction transaction = new Transaction();
        transaction.setPackageInfo(packageToAdd);
        transaction.setPayingUser(payingUser);
        transaction.setAmount(dueAmount);
        transaction.setPaymentMethod(paymentMethod);

        packageToAdd.setTransaction(transaction);

        this.packageRepository.save(packageToAdd);
        this.transactionRepository.save(transaction);

       return this.modelMapper.map(packageToAdd, PackageResponseDto.class);
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
