package com.dpro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dpro.domains.Instructor;
import com.dpro.domains.Student;
import com.dpro.services.InstructorService;
import com.dpro.services.StudentService;

@Controller
public class AdminController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	InstructorService instructorService;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String index() {
		return "admin";
	}
	
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String students(Model model) {
		List<Student> students = studentService.findAll();
		model.addAttribute("students", students);
		
		return "students";
	}
	

	@RequestMapping(value = "/instructors", method = RequestMethod.GET)
	public String instructors(Model model) {
		List<Instructor> instructors = instructorService.findAll();
		model.addAttribute("instructors", instructors);
		
		return "instructors";
	}
}
