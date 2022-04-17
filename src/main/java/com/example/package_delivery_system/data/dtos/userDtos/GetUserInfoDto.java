package com.example.package_delivery_system.data.dtos.userDtos;

import com.example.package_delivery_system.data.dtos.roleDtos.GetRoleInfoDto;
import com.example.package_delivery_system.data.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserInfoDto {

    private Long id;

    private String username;

    private String fullName;

    private String email;

    private String phoneNumber;

    private Address address;

    private Set<GetRoleInfoDto> roles;
}
