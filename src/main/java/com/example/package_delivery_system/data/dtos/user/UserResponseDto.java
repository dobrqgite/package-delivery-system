package com.example.package_delivery_system.data.dtos.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

    private Long id;

    private String username;

    private String email;
}
