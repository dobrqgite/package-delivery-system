package com.example.package_delivery_system.web.controllers;

import com.example.package_delivery_system.data.entities.User;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {

    //SESSION LISTENER!
    protected boolean isLogged(HttpServletRequest request) {
        User userId = (User) request.getSession().getAttribute("userId");

        return userId != null;
    }

}
