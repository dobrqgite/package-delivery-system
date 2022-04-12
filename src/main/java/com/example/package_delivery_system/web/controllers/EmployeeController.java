package com.example.package_delivery_system.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeController {


    @RequestMapping(value = "/agent/agent-home", method = RequestMethod.GET)
    public String getAgentHome(){
        return "/employee/agent/agent_home";
    }

    @RequestMapping(value = "/driver/driver-home", method = RequestMethod.GET)
    public String getDriverHome(){
        return "/employee/driver/driver_home";
    }

}
