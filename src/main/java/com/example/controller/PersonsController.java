package com.example.controller;

import com.example.model.Persons;
import com.example.repository.PersonsRepository;
import com.example.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//Spring RestController annotation is used to create RESTful web services using Spring MVC. Spring RestController takes care of mapping request data to the defined request handler method. Once response body is generated from the handler method, it converts it to JSON or XML response.
@RequestMapping("/persons")
@Slf4j
public class PersonsController {


    private final PersonService personsService;

    public PersonsController(PersonService personsService) {
        this.personsService = personsService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Persons> getAllPersons() {
        log.debug("inside getAllPersons method");
        return personsService.getAllPersons();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Persons getPersonById( @PathVariable int id) {
        log.debug("inside getPersonById method");
        return personsService.getPersonById(id);
    }

    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addPerson(@RequestBody Persons person) {
        log.debug("inside getPersonById method");
        personsService.savePerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<Persons> getPersonsByColor( @PathVariable String color) {
        log.debug("inside getPersonsByColor method");
        return personsService.getPersonsByColor(color);
    }

}
