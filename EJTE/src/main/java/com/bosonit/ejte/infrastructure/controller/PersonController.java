package com.bosonit.ejte.infrastructure.controller;

import com.bosonit.ejte.application.service.PersonService;
import com.bosonit.ejte.domain.Person;
import com.bosonit.ejte.infrastructure.dto.PersonDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO) throws Exception {
        return new PersonDTO(personService.addPerson(personDTO));
    }

    @GetMapping
    public List<PersonDTO> getAll() throws Exception {
        return personService.getPersons().stream().map(PersonDTO::new).toList();
    }

    @GetMapping("/{id}")
    public Person getPersonId(@PathVariable("id") Integer id) throws Exception {
        return personService.getPersonId(id);
    }

    @GetMapping("/name/{name}")
    public List<PersonDTO> getPersonName(@PathVariable("name") String name) throws Exception {
        return personService.getPersonName(name).stream().map(PersonDTO::new).toList();
    }

    @PutMapping("{id}")
    public PersonDTO updatePerson(@PathVariable("id") Integer id, @RequestBody PersonDTO personDTO) throws Exception {
        return new PersonDTO(personService.updatePerson(id, new Person(personDTO)));
    }

    @DeleteMapping("{id}")
    public String deletePerson(@PathVariable("id") Integer id) throws Exception {
        try {
            personService.deletePerson(id);
            return "The person with id " + id + " has been successfully eliminated.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
