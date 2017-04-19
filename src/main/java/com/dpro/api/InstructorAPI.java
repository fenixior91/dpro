package com.dpro.api;

import com.dpro.domains.Instructor;
import com.dpro.domains.Subject;
import com.dpro.services.InstructorService;
import com.dpro.services.SubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class InstructorAPI {

    @Autowired
    InstructorService instructorService;

    @Autowired
    SubjectService subjectService;

    @GetMapping("/instructor/list")
    public List<Instructor> getInstructors() {
        return instructorService.findAll();
    }

    @GetMapping("/instructor/{id}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable Long id) {
        Instructor instructor = instructorService.findById(id);
        
        if (instructor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }
    
    @GetMapping("/instructor/{id}/subject/list")
    public List<Subject> getSubjects(@PathVariable Long id) {
        return subjectService.findAllInUser(id);
    }
    
    @GetMapping("/instructor/{id}/subject/not/list")
    public List<Subject> getNotSubjects(@PathVariable Long id) {
        return subjectService.findAllNotInUser(id);
    }
}
