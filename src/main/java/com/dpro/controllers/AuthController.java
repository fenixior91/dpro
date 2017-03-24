package com.dpro.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login_success", method = RequestMethod.GET)
    public String loginSuccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        String targetUrl = "";
        System.out.println(role);
        if(role.contains("ADMIN")) {
            targetUrl = "/admin/";
        } else if(role.contains("INSTRUCTOR")) {
            targetUrl = "/instructor/";
        } else if (role.contains("STUDENT")){
            targetUrl = "/student/";
        }

        return "redirect:" + targetUrl;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return "403";
    }
}
