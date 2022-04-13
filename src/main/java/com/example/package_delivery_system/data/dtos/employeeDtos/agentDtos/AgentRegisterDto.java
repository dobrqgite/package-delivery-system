package com.example.package_delivery_system.data.dtos.employeeDtos.agentDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class AgentRegisterDto {

    @NotNull
    private String agentUsername;

    @NotNull
    private String agentFirstName;

    @NotNull
    private String agentLastName;

    @NotNull
    private String agentPhone;

    @NotNull
    private String agentUCN;

    @NotNull
    private String agentCountry;

    @NotNull
    private String agentCity;

    @NotNull
    private String agentFullAddress;

    @Email
    @NotNull
    private String agentEmail;

    @NotNull
    private String agentPassword;

    @NotNull
    private String agentConfirmPassword;
}
