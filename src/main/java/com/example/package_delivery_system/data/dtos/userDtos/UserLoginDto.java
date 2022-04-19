package com.example.package_delivery_system.data.dtos.userDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

}