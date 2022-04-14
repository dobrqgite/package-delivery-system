//package com.example.package_delivery_system;
//
//import com.example.package_delivery_system.data.dtos.packageDtos.CreatePackageDto;
//import com.example.package_delivery_system.data.dtos.packageDtos.PackageResponseDto;
//import com.example.package_delivery_system.data.dtos.userDtos.UserRegisterDto;
//import com.example.package_delivery_system.data.entities.Address;
//import com.example.package_delivery_system.data.entities.Package;
//import com.example.package_delivery_system.data.entities.Role;
//import com.example.package_delivery_system.data.entities.UserEntity;
//import com.example.package_delivery_system.data.repositories.*;
//import com.example.package_delivery_system.services.api.AddressService;
//import com.example.package_delivery_system.services.api.UserService;
//import com.example.package_delivery_system.services.impl.PackageServiceImpl;
//import com.example.package_delivery_system.services.impl.UserServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Set;
//
//@RunWith(MockitoJUnitRunner.class)
//public class PackageServiceImplTest {
//
//    private MockMvc mockMvc;
//
//    ModelMapper modelMapper;
//    @Mock
//    UserRepository userRepository;
//    @Mock
//    PasswordEncoder passwordEncoder;
//    @Mock
//    RoleRepository roleRepository;
//    @Mock
//    AddressService addressService;
//    @Mock
//    AddressRepository addressRepository;
//
//
//    @Mock
//    private TransactionRepository transactionRepository;
//
//    @Mock
//    private PackageRepository packageRepository;
//
//    @InjectMocks
//    private PackageServiceImpl packageService;
//
//    @Test
//    public void shitShouldFuckingWorkForFucksSake(){
//        final UserService userService = new UserServiceImpl(userRepository, passwordEncoder,
//                roleRepository, addressService, addressRepository);
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////    @Test
////    public void saveTransaction_And_Package_Success(){
////
////
////        CreatePackageDto createPackageDto = new CreatePackageDto("hui", BigDecimal.valueOf(10), BigDecimal.valueOf(10),
////                BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(10), "asd@abv.bg", "test@abv.bg", "VISA");
////
////        PackageResponseDto responseDto = new PackageResponseDto();
////        String loggedInUserEmail = "namaikatiputkata@huinqsmazana.com";
////
////        Mockito.when(packageService.addPackage(createPackageDto, loggedInUserEmail)).thenReturn(responseDto);
////        //act
////
////        packageService.addPackage(createPackageDto, loggedInUserEmail);
////        //assert
////        assert (packageRepository.count() > 0);
////
////    }
//}