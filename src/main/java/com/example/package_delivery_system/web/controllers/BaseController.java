package com.example.package_delivery_system.web.controllers;

import com.example.package_delivery_system.data.entities.User;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    protected boolean isLogged(HttpServletRequest request) {
        User userId = (User) request.getSession().getAttribute("userId");

        return userId != null;
    }

}
