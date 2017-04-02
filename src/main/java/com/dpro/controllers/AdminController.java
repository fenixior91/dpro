package com.dpro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String index() {
		return "admin";
	}
	
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String students() {
		return "students";
	}
}
