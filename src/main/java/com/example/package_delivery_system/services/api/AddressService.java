<<<<<<< Updated upstream:src/main/java/com/example/package_delivery_system/services/AddressService.java
package com.example.package_delivery_system.services;
=======
package com.example.package_delivery_system.services.api;
>>>>>>> Stashed changes:src/main/java/com/example/package_delivery_system/services/api/AddressService.java

import com.example.package_delivery_system.data.dtos.userDtos.UserRegisterDto;
import com.example.package_delivery_system.data.entities.Address;

public interface AddressService {

    Address createUserAddress(UserRegisterDto userAddressFromRegisterDto);

}
