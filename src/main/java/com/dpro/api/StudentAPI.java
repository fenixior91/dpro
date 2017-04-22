package com.dpro.api;

import com.dpro.domains.Student;
import com.dpro.services.StudentService;
import com.dpro.services.SubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontroler restowy, do asynchronicznego manipulowania danymi wykładowców
 *
 * @author Tomasz Truszkowski
 */
@RestController

@RequestMapping(value = "/api")
public class StudentAPI {

    @Autowired
    StudentService studentService;

    @Autowired
    SubjectService subjectService;

    /**
     * Lista wykadowców w formacie JSON.
     *
     * @return lista wykadowców w formacie JSON
     */
    @GetMapping("/student/list")
    public List<Student> getStudents() {
        return studentService.findAll();
    }

    /**
     * Jeżeli pobieranie się powiedzie zostanie zwrócony obiekt wykładowcy w
     * formacie JSON i status OK, w przeciwnym wypadku status NOT_FOUND.
     *
     * @param id identyfikator wykładowcy
     * @return obiekt wykładowcy w formacie JSON
     */
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.findById(id);

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
