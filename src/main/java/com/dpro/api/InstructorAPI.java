package com.dpro.api;

import com.dpro.domains.Instructor;
import com.dpro.domains.Subject;
import com.dpro.services.InstructorService;
import com.dpro.services.SubjectService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontroler restowy, do asynchronicznego manipulowania danymi wykładowców
 *
 * @author Tomasz Truszkowski
 */
@RestController

@RequestMapping(value = "/api")
public class InstructorAPI {

    @Autowired
    InstructorService instructorService;

    @Autowired
    SubjectService subjectService;

    /**
     * Lista wykadowców w formacie JSON.
     *
     * @return lista wykadowców w formacie JSON
     */
    @GetMapping("/instructor/list")
    public List<Instructor> getInstructors() {
        return instructorService.findAll();
    }

    /**
     * Jeżeli pobieranie się powiedzie zostanie zwrócony obiekt wykładowcy w
     * formacie JSON i status OK, w przeciwnym wypadku status NOT_FOUND.
     *
     * @param id identyfikator wykładowcy
     * @return obiekt wykładowcy w formacie JSON
     */
    @GetMapping("/instructor/{id}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable Long id) {
        Instructor instructor = instructorService.findById(id);

        if (instructor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    /**
     * Pobiera listę przedmiotów przypisanych do konkretnego użytkownika
     *
     * @param id identyfikator użytkownika
     * @return lista przedmiotów przypisanych do konrektengo użytkownika
     */
    @GetMapping("/instructor/{id}/subject/list")
    public List<Subject> getSubjects(@PathVariable Long id) {
        return subjectService.findAllInUser(id);
    }

    /**
     * Pobiera listę przedmiotów, które nie są przypisane do konkretnego
     * użytkownika
     *
     * @param id identyfikator użytkownika
     * @return lista przedmiotów, które nie są przypisane do konkretnego
     * użytkownika
     */
    @GetMapping("/instructor/{id}/subject/not/list")
    public List<Subject> getNotSubjects(@PathVariable Long id) {
        return subjectService.findAllNotInUser(id);
    }

    /**
     * Przypisuje przedmioty do użytkownika
     *
     * @param id identyfikator użytkownika
     * @param subjects lista przedmiotów do przypisania
     * @return status ok i kod 200, jeżeli się powiodło, w przeciwnym wypadku
     * status ok error i kod 200
     */
    @RequestMapping(value = "/instructor/{id}/subject/attach", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, String>> attach(@PathVariable Long id, @RequestBody List<Subject> subjects) {
        subjectService.attachToUser(id, subjects);

        Map<String, String> result = new HashMap<>();
        result.put("status", "ok");
        result.put("code", "200");

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
