package com.dpro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InstructorController {
    @RequestMapping(value = "/instructor", method = RequestMethod.GET)
    public String index() {
        return "instructor";
    }
}
