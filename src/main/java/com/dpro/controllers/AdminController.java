package com.dpro.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.dpro.domains.Instructor;
import com.dpro.domains.Student;
import com.dpro.services.InstructorService;
import com.dpro.services.StudentService;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/admin/students", method = RequestMethod.GET)
    public String students(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        return "students";
    }

    @RequestMapping(value = "/admin/instructors", method = RequestMethod.GET)
    public String instructors(Model model) {
        List<Instructor> instructors = instructorService.findAll();
        model.addAttribute("instructors", instructors);

        return "instructors";
    }

    @RequestMapping(value = "/admin/instructor/create", method = RequestMethod.GET)
    public String getCreateInstructor() {
        return "create_instructor";
    }

    @RequestMapping(value = "/admin/instructor/create", method = RequestMethod.POST)
    public String postCreateInstructor(@RequestParam Map<String, String> params) {
        if (instructorService.create(params)) {
            return "redirect:/admin/instructors";
        } else {
            return "redirect:/500";
        }
    }

    @RequestMapping(value = "/admin/instructor/update/{id}", method = RequestMethod.GET)
    public String getUpdateInstructor(@PathVariable Long id, Model model) {
        model.addAttribute("instructor", instructorService.findById(id));
        return "edit_instructor";
    }

    @RequestMapping(value = "/admin/instructor/update", method = RequestMethod.POST)
    public String postUpdateInstructor(@RequestParam Map<String, String> params) {
        if (instructorService.update(params)) {
            return "redirect:/admin/instructors";
        } else {
            return "redirect:/500";
        }
    }

//	@RequestMapping(value = "/admin/instructor/attach_subjects", method = RequestMethod.POST)
//	public String postAttachSubjects(@ModelAttribute Instructor instructor) {
//		if (instructorService.update(instructor)) {
//			return "redirect:/admin/instructors";
//		} else {
//			return "redirect:/500";
//		}
//	}
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("YYYY-MM-DD"), true);
        binder.registerCustomEditor(Date.class, editor);
    }
}
