package com.bosonit.bp1.content.infrastructure.controller;

import com.bosonit.bp1.content.application.port.IAddPersonPort;
import com.bosonit.bp1.content.application.port.IDeletePersonPort;
import com.bosonit.bp1.content.application.port.IGetPersonPort;
import com.bosonit.bp1.content.application.port.IUpdatePersonPort;
import com.bosonit.bp1.content.infrastructure.controller.dto.input.PersonInputDTO;
import com.bosonit.bp1.content.infrastructure.controller.dto.output.PersonOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    IAddPersonPort iAddPersonPort;
    @Autowired
    IGetPersonPort iGetPersonPort;
    @Autowired
    IUpdatePersonPort iUpdatePersonPort;
    @Autowired
    IDeletePersonPort iDeletePersonPort;

    @PostMapping
    public PersonOutputDTO addPerson(@RequestBody PersonInputDTO personInputDTO) throws Exception {
        return iAddPersonPort.addPerson(personInputDTO);
    }

    @GetMapping
    public List<PersonOutputDTO> getPersons() {
        return iGetPersonPort.getPersons();
    }

    @GetMapping("/{id}")
    public PersonOutputDTO getPersonId(@PathVariable("id") int id) throws Exception {
        return iGetPersonPort.getPersonId(id);
    }

    @GetMapping("/name/{name}")
    public List<PersonOutputDTO> getPersonName(@PathVariable("name") String name) throws Exception {
        return iGetPersonPort.getPersonName(name);
    }

    @PutMapping("/{id}")
    public PersonOutputDTO updatePerson(@PathVariable("id") int id, @RequestBody PersonInputDTO personInputDTO)
            throws Exception {
        return iUpdatePersonPort.updatePerson(id, personInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") int id) throws Exception {
        iDeletePersonPort.deletePerson(id);
    }
}
