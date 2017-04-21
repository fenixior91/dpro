package com.dpro.controllers;

import com.dpro.domains.Instructor;
import com.dpro.domains.Student;
import com.dpro.services.InstructorService;
import com.dpro.services.StudentService;
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

/**
 * Kontroler do obsługi żądań w panelu administracyjnym
 *
 * @author Tomasz Truszkowski
 */
@Controller
public class AdminController {

    @Autowired
    InstructorService instructorService;

    @Autowired
    StudentService studentService;

    /**
     * Lista wykładowców
     *
     * @param model model z którego będzie korzystać strona jsp
     * @return strona jsp z listą wykładowców
     */
    @RequestMapping(value = "/admin/instructor/list", method = RequestMethod.GET)
    public String instructors(Model model) {
        List<Instructor> instructors = instructorService.findAll();
        model.addAttribute("instructors", instructors);

        return "instructors";
    }

    /**
     * Pobiera formularz do tworzenia wykładowcy
     *
     * @return strona jsp z formularzem tworzenia wykładwocy
     */
    @RequestMapping(value = "/admin/instructor/create", method = RequestMethod.GET)
    public String getCreateInstructor() {
        return "create_instructor";
    }

    /**
     * Tworzy nowego wykładowcę
     *
     * @param instructor przesłane parametry wykładowcy z formularza
     * @return przekierowanie do listy wykładowców, w przeciwnym wypadku do
     * strony błędu
     */
    @RequestMapping(value = "/admin/instructor/create", method = RequestMethod.POST)
    public String postCreateInstructor(Instructor instructor) {
        if (instructorService.create(instructor)) {
            return "redirect:/admin/instructor/list";
        }

        return "redirect:/500";
    }

    /**
     * Pobiera formularz do aktualizacji wykładowcy
     *
     * @param id identyfikator wykładowcy
     * @param model model z którego będzie korzystać strona jsp
     * @return strona jsp z formularzem aktualizacji wykładowcy
     */
    @RequestMapping(value = "/admin/instructor/edit/{id}", method = RequestMethod.GET)
    public String getEditInstructor(@PathVariable Long id, Model model) {
        model.addAttribute("instructor", instructorService.findById(id));

        return "edit_instructor";
    }

    /**
     * Aktualizuje wykładowcę
     *
     * @param instructor przesłane parametry wykładowcy z formularza
     * @return przekierowanie do listy wykładowców, w przeciwnym wypadku do
     * strony błędu
     */
    @RequestMapping(value = "/admin/instructor/update", method = RequestMethod.POST)
    public String postUpdateInstructor(Instructor instructor) {
        if (instructorService.update(instructor)) {
            return "redirect:/admin/instructor/list";
        }

        return "redirect:/500";
    }

    /**
     * Lista studentów
     *
     * @param model model z którego będzie korzystać strona jsp
     * @return strona jsp z listą studentów
     */
    @RequestMapping(value = "/admin/student/list", method = RequestMethod.GET)
    public String students(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        return "students";
    }

    /**
     * Pobiera formularz do tworzenia studenta
     *
     * @return strona jsp z formularzem tworzenia studenta
     */
    @RequestMapping(value = "/admin/student/create", method = RequestMethod.GET)
    public String getCreateStudent() {
        return "create_student";
    }

    /**
     * Tworzy nowego studenta
     *
     * @param student przesłane parametry studenta z formularza
     * @return przekierowanie do listy studentów, w przeciwnym wypadku do strony
     * błędu
     */
    @RequestMapping(value = "/admin/student/create", method = RequestMethod.POST)
    public String postCreateStudent(Student student) {
        if (studentService.create(student)) {
            return "redirect:/admin/student/list";
        }

        return "redirect:/500";
    }

    /**
     * Pobiera formularz do aktualizacji studenta
     *
     * @param id identyfikator studenta
     * @param model model z którego będzie korzystać strona jsp
     * @return strona jsp z formularzem aktualizacji studenta
     */
    @RequestMapping(value = "/admin/student/edit/{id}", method = RequestMethod.GET)
    public String getEditStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));

        return "edit_student";
    }

    /**
     * Aktualizuje studenta
     *
     * @param student przesłane parametry studenta z formularza
     * @return przekierowanie do listy studentaów, w przeciwnym wypadku do
     * strony błędu
     */
    @RequestMapping(value = "/admin/student/update", method = RequestMethod.POST)
    public String postUpdateStudent(Student student) {
        if (studentService.update(student)) {
            return "redirect:/admin/student/list";
        }

        return "redirect:/500";
    }

    /**
     * Strona główna panelu administracyjnego
     *
     * @return strona jsp strony głównej panelu administracyjnego
     */
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
