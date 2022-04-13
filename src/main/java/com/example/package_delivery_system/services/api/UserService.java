<<<<<<< Updated upstream:src/main/java/com/example/package_delivery_system/services/UserService.java
package com.example.package_delivery_system.services;
=======
package com.example.package_delivery_system.services.api;
>>>>>>> Stashed changes:src/main/java/com/example/package_delivery_system/services/api/UserService.java

import com.example.package_delivery_system.data.dtos.userDtos.UserRegisterDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserResponseDto;
import com.example.package_delivery_system.data.dtos.userDtos.UserUpdateDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserResponseDto register(UserRegisterDto userRegisterDto);

    UserResponseDto editCredentials(UserUpdateDto userUpdateDto);

    void seedRoles();
}
