package com.bosonit.docker.person.infrastructure.controller;

import com.bosonit.docker.person.application.port.AddPersonPort;
import com.bosonit.docker.person.application.port.DeletePersonPort;
import com.bosonit.docker.person.application.port.GetPersonPort;
import com.bosonit.docker.person.application.port.UpdatePersonPort;
import com.bosonit.docker.person.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.docker.person.infrastructure.dto.output.PersonOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    AddPersonPort addPersonPort;
    @Autowired
    DeletePersonPort deletePersonPort;
    @Autowired
    GetPersonPort getPersonPort;
    @Autowired
    UpdatePersonPort updatePersonPort;

    @PostMapping
    public PersonOutputDTO addPerson(@Valid @RequestBody PersonInputDTO personInputDTO) {
        return addPersonPort.addPerson(personInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") String id) {
        deletePersonPort.deletePerson(id);
    }

    @GetMapping
    public List<PersonOutputDTO> getPersons() {
        return getPersonPort.getPersons();
    }

    @GetMapping("/{id}")
    public PersonOutputDTO getPersonId(@PathVariable("id") String id) {
        return getPersonPort.getPersonId(id);
    }

    @GetMapping("/name/{name}")
    public List<PersonOutputDTO> getPersonName(@PathVariable("name") String username) throws Exception {
        return getPersonPort.getPersonName(username);
    }

    @PutMapping("/{id}")
    public PersonOutputDTO updatePerson(@PathVariable("id") String id, @RequestBody PersonInputDTO personInputDTO) {
        return updatePersonPort.updatePerson(id, personInputDTO);
    }
}
