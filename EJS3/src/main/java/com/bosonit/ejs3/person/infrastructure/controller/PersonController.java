package com.bosonit.ejs3.person.infrastructure.controller;

import com.bosonit.ejs3.person.infrastructure.dto.PersonDTO;
import com.bosonit.ejs3.person.application.service.PersonService;
import com.bosonit.ejs3.person.domain.Person;
import com.bosonit.ejs3.person.infrastructure.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @PostMapping
    public Person addPerson(@RequestBody PersonDTO personDTO) {
        return personService.addPerson(personDTO);
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") String idPerson) throws Exception {
        return personService.getPersonById(idPerson);
    }

    @GetMapping("/username/{username}")
    public Person getPersonByUsername(@PathVariable("username") String username) {
        return personService.getPersonByUsername(username);
    }

    @PutMapping("/{id}")
    public PersonDTO updatePerson(@PathVariable("id") String idPerson, @RequestBody PersonDTO personDTO) {
        return new PersonDTO(personService.updatePerson(idPerson, personDTO));
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") String idPerson) {
        try {
            personService.deletePerson(idPerson);
            return "The person with idPerson " + idPerson + " has been successfully eliminated.";
        } catch (Exception ex) {
            return "Error: " + ex.getMessage();
        }
    }
}
