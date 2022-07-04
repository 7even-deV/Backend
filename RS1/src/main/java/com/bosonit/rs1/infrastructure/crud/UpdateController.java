package com.bosonit.rs1.infrastructure.crud;

import com.bosonit.rs1.application.interfaces.IPersonService;
import com.bosonit.rs1.domain.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class UpdateController {

    @Autowired
    IPersonService iPersonService;

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable("id") int id, @RequestBody Person person) {
        Person foundPerson = iPersonService.findPerson(id);
        Optional<Person> personOptional = Optional.of(person);
        foundPerson.setName(personOptional.map(Person::getName).orElse(foundPerson.getName()));
        foundPerson.setAge(personOptional.map(Person::getAge).orElse(foundPerson.getAge()));
        foundPerson.setCity(personOptional.map(Person::getCity).orElse(foundPerson.getCity()));
        return foundPerson;
    }
}
