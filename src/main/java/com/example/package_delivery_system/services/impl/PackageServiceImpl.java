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


        //TODO:Not sure how to set user to package!!!!!MUST FIX!!!

        String content = createPackageDto.getContent();
        BigDecimal weight = createPackageDto.getWeight();
        BigDecimal height = createPackageDto.getHeight();
        BigDecimal width = createPackageDto.getWidth();
        BigDecimal length = createPackageDto.getLength();


        //Calculate tax
        //TODO: extract method for tax calculation
        //Never in my time with programming have I written such utterly disgusting code...


        //RETHINK logic -NEW METHOD FOR CALCULATION!!!!

//       if (height.doubleValue() <= 61
//               && width.doubleValue() <= 44
//               && length.doubleValue() <= 37) {
//           if (weight.doubleValue() >= 1 && weight.doubleValue() <= 10){
//               createPackageDto
//                       .setTax(BigDecimal.valueOf((calculatedTax) + (calculatedTax / 5.5)));
//           }else if(weight.doubleValue() >= 10 && weight.doubleValue() <= 20){
//               createPackageDto
//                       .setTax(BigDecimal.valueOf((calculatedTax) + (calculatedTax / 4.5)));
//           }else if(weight.doubleValue() >= 20 && weight.doubleValue() <= 50) {
//               createPackageDto
//                       .setTax(BigDecimal.valueOf((calculatedTax) + (calculatedTax / 3)));
//           }else{
//               createPackageDto
//                       .setTax(BigDecimal.valueOf((calculatedTax) + (calculatedTax / 1.5)));
//           }
//       } else if (height.doubleValue() >= 61
//               && width.doubleValue() >= 44
//               && length.doubleValue() >= 37
//               && height.doubleValue() <= 90
//               && width.doubleValue() <= 90
//               && length.doubleValue() <= 90) {
//           if (weight.doubleValue() >= 1 && weight.doubleValue() <= 10){
//               createPackageDto
//                       .setTax(BigDecimal.valueOf((calculatedTax) + (calculatedTax / 4.4)));
//           }else if(weight.doubleValue() >= 10 && weight.doubleValue() <= 20){
//               createPackageDto
//                       .setTax(BigDecimal.valueOf((calculatedTax) + (calculatedTax / 4)));
//           }else if(weight.doubleValue() >= 20 && weight.doubleValue() <= 50) {
//               createPackageDto
//                       .setTax(BigDecimal.valueOf((calculatedTax) + (calculatedTax / 2.4)));
//           }else{
//               createPackageDto
//                       .setTax(BigDecimal.valueOf((calculatedTax) + (calculatedTax / 1.5)));
//           }
//       } else if(height.doubleValue() > 90
//               && width.doubleValue() > 90
//               && length.doubleValue() > 90){
//           if (weight.doubleValue() >= 1 && weight.doubleValue() <= 10){
//           createPackageDto
//                   .setTax(BigDecimal.valueOf((calculatedTax) + (calculatedTax / 4)));
//           }else if(weight.doubleValue() >= 10 && weight.doubleValue() <= 20){
//               createPackageDto
//                       .setTax(BigDecimal.valueOf((calculatedTax) + (calculatedTax / 3.6)));
//           }else if(weight.doubleValue() >= 20 && weight.doubleValue() <= 50){
//               createPackageDto
//                       .setTax(BigDecimal.valueOf((calculatedTax) + (calculatedTax / 2)));
//           }else{
//               createPackageDto
//                       .setTax(BigDecimal.valueOf((calculatedTax) + (calculatedTax / 1.5)));
//           }
//       }


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
        if(weight <= 1){
            return 1.1;
        }
        if(weight > 1 && weight <= 10){
            return 1.15;
        }
        if (weight > 10 && weight <= 30){
            return 1.20;
        }
        if (weight > 30 && weight <= 60){
            return 1.30;
        }
        return 1.7;
    }

    private double calculateSizeMultiplier(double doubleValue, double doubleValue1, double doubleValue2) {
        //TODO: add calculations
        return 1.1;
    }
}
