package com.dpro.api;

import com.dpro.domains.Student;
import com.dpro.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class StudentAPI {
    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(value = "/student/", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listStudents() {
        List<Student> students = studentRepository.findAll();

        if (students.isEmpty())
            return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);

    }

    @RequestMapping(value="/student/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable(value = "id") long id) {
        Student student = studentRepository.findById(id);

        if (student == null)
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
}
