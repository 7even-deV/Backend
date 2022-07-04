package com.bosonit.rs1.infrastructure.crud;

import com.bosonit.rs1.application.interfaces.IPersonService;
import com.bosonit.rs1.domain.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class ReadController {

    @Autowired
    IPersonService iPersonService;

    @GetMapping("/{id}")
    public Person findPersonId(@PathVariable("id") int id) {
        return iPersonService.findPerson(id);
    }

    @GetMapping("name/{name}")
    public Person findPersonName(@PathVariable("name") String name) {
        return iPersonService.findPerson(name);
    }
}
