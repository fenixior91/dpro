package com.dpro.controllers;

import com.dpro.services.SubjectService;
import com.dpro.services.SubjectTypeService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    SubjectTypeService subjectTypeService;

    @RequestMapping(value = "/admin/subject/list", method = RequestMethod.GET)
    public String adminSubjects(Model model) {
        model.addAttribute("subjects", subjectService.findAll());

        return "subjects";
    }

    @RequestMapping(value = "/admin/subject/show/{id}", method = RequestMethod.GET)
    public String subject(@PathVariable Long id, Model model) {
        model.addAttribute("subject", subjectService.findById(id));

        return "subject";
    }

    @RequestMapping(value = "/admin/subject/create", method = RequestMethod.GET)
    public String getCreateSubject(Model model) {
        model.addAttribute("subjectTypes", subjectTypeService.findAll());

        return "create_subject";
    }

    @RequestMapping(value = "/admin/subject/create", method = RequestMethod.POST)
    public String postCreateSubject(@RequestParam Map<String, String> params) {
        if (subjectService.create(params)) {
            return "redirect:/admin/subject/list";
        }

        return "redirect:/500";
    }

    @RequestMapping(value = "/admin/subject/edit/{id}", method = RequestMethod.GET)
    public String getEditSubject(@PathVariable Long id, Model model) {
        model.addAttribute("subject", subjectService.findById(id));
        model.addAttribute("subjectTypes", subjectTypeService.findAll());

        return "edit_subject";
    }

    @RequestMapping(value = "/admin/subject/update", method = RequestMethod.POST)
    public String postUpdateSubject(@RequestParam Map<String, String> params) {
        if (subjectService.update(params)) {
            return "redirect:/admin/subject/list";
        }

        return "redirect:/500";
    }

    @RequestMapping(value = "/admin/subject_type/create", method = RequestMethod.GET)
    public String getCreateSubjectType() {
        return "create_subject_type";
    }

    @RequestMapping(value = "/admin/subject_type/create", method = RequestMethod.POST)
    public String postCreateSubjectType(@RequestParam Map<String, String> params) {
        if (subjectTypeService.create(params)) {
            return "redirect:/admin/subject/list";
        }

        return "redirect:/500";
    }

    @RequestMapping(value = "/instructor/subject/list", method = RequestMethod.GET)
    public String instructorSubjects(Model model) {
        return "subjects";
    }

    @RequestMapping(value = "/student/subject/list", method = RequestMethod.GET)
    public String stuentSubjects(Model model) {
        return "subjects";
    }
}
