package com.dpro.controllers;

import com.dpro.domains.Subject;
import com.dpro.domains.SubjectType;
import com.dpro.services.SubjectService;
import com.dpro.services.SubjectTypeService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     *
     * @param subject
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/subject/create", method = RequestMethod.GET)
    public String getCreateSubject(Model model) {
        model.addAttribute("subjectTypes", subjectTypeService.findAll());
        
        return "create_subject";
    }
    
    @RequestMapping(value = "/admin/subject/create", method = RequestMethod.POST)
    public String postCreateSubject(@RequestParam Map<String, String> params, Model model) {
        return "subjects";
    }

    @RequestMapping(value = "/instructor/subjects", method = RequestMethod.GET)
    public String instructorSubjects(Model model) {
        return "subjects";
    }

    @RequestMapping(value = "/student/subjects", method = RequestMethod.GET)
    public String stuentSubjects(Model model) {
        return "subjects";
    }
}
