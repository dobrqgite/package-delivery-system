package com.example.package_delivery_system.data.dtos.employeeDtos.agentDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgentResponseDto {
    private Long id;

    private String username;

    private String fullName;

    private String phone;

    private String UCN;

    private String email;
}