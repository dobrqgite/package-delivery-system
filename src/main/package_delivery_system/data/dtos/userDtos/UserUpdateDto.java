package com.example.package_delivery_system.data.dtos.userDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDto {

    private String username;

    private String fullName;

    private String email;

    private String phoneNumber;
}
