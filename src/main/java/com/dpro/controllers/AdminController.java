package com.dpro.controllers.admin;

import com.dpro.domains.Instructor;
import com.dpro.domains.Student;
import com.dpro.services.InstructorService;
import com.dpro.services.StudentService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    InstructorService instructorService;

    @Autowired
    StudentService studentService;

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
        }

        return "redirect:/500";
    }

    @RequestMapping(value = "/admin/instructor/edit/{id}", method = RequestMethod.GET)
    public String getEditInstructor(@PathVariable Long id, Model model) {
        model.addAttribute("instructor", instructorService.findById(id));

        return "edit_instructor";
    }

    @RequestMapping(value = "/admin/instructor/update", method = RequestMethod.POST)
    public String postUpdateInstructor(@RequestParam Map<String, String> params) {
        if (instructorService.update(params)) {
            return "redirect:/admin/instructors";
        }

        return "redirect:/500";
    }

    @RequestMapping(value = "/admin/students", method = RequestMethod.GET)
    public String students(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        return "students";
    }

    @RequestMapping(value = "/admin/student/create", method = RequestMethod.GET)
    public String getCreateStudent() {
        return "create_student";
    }

    @RequestMapping(value = "/admin/student/create", method = RequestMethod.POST)
    public String postCreateStudent(@RequestParam Map<String, String> params) {
        if (studentService.create(params)) {
            return "redirect:/admin/students";
        }

        return "redirect:/500";
    }

    @RequestMapping(value = "/admin/student/edit/{id}", method = RequestMethod.GET)
    public String getEditStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));

        return "edit_student";
    }

    @RequestMapping(value = "/admin/student/update", method = RequestMethod.POST)
    public String postUpdateStudent(@RequestParam Map<String, String> params) {
        if (studentService.update(params)) {
            return "redirect:/admin/students";
        }

        return "redirect:/500";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String index() {
        return "admin";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("YYYY-MM-DD"), true);
        binder.registerCustomEditor(Date.class, editor);
    }
}
