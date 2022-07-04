package com.bosonit.rs1.infrastructure.crud;

import com.bosonit.rs1.application.interfaces.IPersonService;
import com.bosonit.rs1.domain.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class CreateController {

    @Autowired
    IPersonService iPersonService;

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        iPersonService.addPerson(person);
        return person;
    }
}
