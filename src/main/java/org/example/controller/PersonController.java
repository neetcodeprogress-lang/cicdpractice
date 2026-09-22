package org.example.controller;

import java.util.List;
import org.example.model.Person;
import org.example.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** HTTP endpoints for persons. */
@RestController
@RequestMapping("/api/persons")
public class PersonController {

  private final PersonService personService;

  /** Injects the service. */
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /** Saves a person and answers 201 with the saved row. */
  @PostMapping
  public ResponseEntity<Person> create(@RequestBody Person person) {
    return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
  }

  /** Returns every person. */
  @GetMapping
  public List<Person> findAll() {
    return personService.findAll();
  }
}
