package com.bosonit.ej2.infrastructure.controller;

import com.bosonit.ej2.application.interfaces.IPersonService;
import com.bosonit.ej2.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.ej2.infrastructure.dto.output.PersonOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    IPersonService iPersonService;

    @PostMapping("/add")
    public PersonOutputDTO addPerson(@RequestBody PersonInputDTO personInputDTO) throws Exception {
        return iPersonService.addPerson(personInputDTO);
    }

    @GetMapping("/id/{id}")
    public PersonOutputDTO getPersonId(@PathVariable("id") int id) throws Exception {
        return iPersonService.getPersonId(id);
    }

    @GetMapping("/name/{name}")
    public List<PersonOutputDTO> getPersonName(@PathVariable("name") String name) throws Exception {
        return iPersonService.getPersonName(name);
    }

    @GetMapping("/all")
    public List<PersonOutputDTO> getPersons() {
        return iPersonService.getPersons();
    }
}
