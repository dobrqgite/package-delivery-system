package com.example.package_delivery_system.data.dtos.userDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class EditUserCredentialsDto {

    @NotNull
    private String username;

    @NotNull
    private String fullName;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;
}
