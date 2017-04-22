package com.dpro.api;

import com.dpro.domains.Subject;
import com.dpro.services.SubjectService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontroler restowy, do asynchronicznego manipulowania danymi użytkownika
 *
 * @author Tomasz Truszkowski
 */
@RestController
@RequestMapping(value = "/api")
public class UserAPI {

    @Autowired
    SubjectService subjectService;

    /**
     * Pobiera listę przedmiotów przypisanych do konkretnego użytkownika
     *
     * @param id identyfikator użytkownika
     * @return lista przedmiotów przypisanych do konrektengo użytkownika
     */
    @RequestMapping(value = {"/instructor/{id}/subject/list", "/student/{id}/subject/list"}, method = RequestMethod.GET)
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
    @RequestMapping(value = {"/instructor/{id}/subject/not/list", "/student/{id}/subject/not/list"}, method = RequestMethod.GET)
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
    @RequestMapping(value = {"/instructor/{id}/subject/attach", "/student/{id}/subject/attach"}, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, String>> attach(@PathVariable Long id, @RequestBody List<Subject> subjects) {
        subjectService.attachToUser(id, subjects);

        Map<String, String> result = new HashMap<>();
        result.put("status", "ok");
        result.put("code", "200");

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
