package com.example.package_delivery_system.data.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserLoginDto {

    @Email
    @NotNull
    private String eMail;

    @NotNull
    private String password;


    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
