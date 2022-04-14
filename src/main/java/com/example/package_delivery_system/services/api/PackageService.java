package com.example.package_delivery_system.services.api;

import com.example.package_delivery_system.data.dtos.packageDtos.CreatePackageDto;
import com.example.package_delivery_system.data.dtos.packageDtos.PackageResponseDto;

public interface PackageService {

    PackageResponseDto addPackage(CreatePackageDto createPackageDto, String currentlyLoggedInUserEmail);

}
