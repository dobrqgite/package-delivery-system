package com.example.package_delivery_system.data.dtos.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

    private Long id;

    private String username;

    private String fullName;

    private String phone;

    private String UCN;

    private String email;

    public UserResponseDto(Long id, String username, String fullName, String phone, String UCN, String email) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.phone = phone;
        this.UCN = UCN;
        this.email = email;
    }
}
