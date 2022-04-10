package com.example.package_delivery_system.services;

import com.example.package_delivery_system.data.dtos.packageDtos.CreatePackageDto;

public interface PackageService {

    void addPackage(CreatePackageDto createPackageDto);

}
