package com.bosonit.dba2.infrastructure.controller;

import com.bosonit.dba2.application.PersonDal;
import com.bosonit.dba2.domain.Person;
import com.bosonit.dba2.infrastructure.dto.PersonDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/person")
@RestController
public class PersonController {

    @Autowired
    PersonDal personDal;

    @PostMapping
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO) {
        return new PersonDTO(personDal.addPerson(new Person(personDTO)));
    }

    @GetMapping
    public List<PersonDTO> getAll() {
        return personDal.getAll().stream().map(PersonDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Person getPersonId(@PathVariable("id") String id) {
        return personDal.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<PersonDTO> getPersonName(@PathVariable("name") String name) {
        return personDal.findByName(name).stream().map(PersonDTO::new).collect(Collectors.toList());
    }

    @PutMapping("{id}")
    public PersonDTO updatePerson(@PathVariable("id") String id, @RequestBody PersonDTO personDTO) {
        return new PersonDTO(personDal.updatePerson(id, new Person(personDTO)));
    }

    @DeleteMapping
    public void deletePerson(@RequestBody PersonDTO personDTO) {
        personDal.deletePerson(new Person(personDTO));
    }
}
