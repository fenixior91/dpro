package com.dpro.api;

import com.dpro.domains.Subject;
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
 * Kontroler restowy, do asynchronicznego manipulowania danymi przedmiotów
 *
 * @author Tomasz Truszkowski
 */
@RestController
@RequestMapping(value = "/api")
public class SubjectAPI {

    @Autowired
    private SubjectService subjectService;

    /**
     * Lista przedmiotów w formacie JSON.
     *
     * @return lista przedmiotów w formacie JSON
     */
    @GetMapping("/subject/list")
    public List<Subject> getSubjects() {
        return subjectService.findAll();
    }

    /**
     * Jeżeli pobieranie się powiedzie zostanie zwrócony obiekt przedmiotu w
     * formacie JSON i status OK, w przeciwnym wypadku status NOT_FOUND.
     *
     * @param id identyfikator przedmiotu
     * @return obiekt przedmiotu w formacie JSON
     */
    @GetMapping("/subject/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
        Subject subject = subjectService.findById(id);

        if (subject == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(subject, HttpStatus.OK);
    }
}
