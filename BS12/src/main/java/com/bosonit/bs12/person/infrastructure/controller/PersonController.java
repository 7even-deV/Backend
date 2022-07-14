package com.bosonit.bs12.person.infrastructure.controller;

import com.bosonit.bs12.feign.IFeignServer;
import com.bosonit.bs12.person.application.port.AddPersonPort;
import com.bosonit.bs12.person.application.port.DeletePersonPort;
import com.bosonit.bs12.person.application.port.GetPersonPort;
import com.bosonit.bs12.person.application.port.UpdatePersonPort;
import com.bosonit.bs12.person.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.bs12.person.infrastructure.dto.output.PersonOutputDTO;
import com.bosonit.bs12.teacher.infrastructure.dto.output.TeacherOutputDTO;

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
    @Autowired
    IFeignServer feignServer;

    @PostMapping
    @CrossOrigin(origins = "*")
    public PersonOutputDTO addPerson(@Valid @RequestBody PersonInputDTO personInputDTO) {
        return addPersonPort.addPerson(personInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") String id) {
        deletePersonPort.deletePerson(id);
    }

    @GetMapping
    @CrossOrigin(origins = "*")
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

    @GetMapping("/teacher/{id}")
    public TeacherOutputDTO getTeacher(@PathVariable("id") String idTeacher) {
        return feignServer.getTeacher(idTeacher);
    }

    @PutMapping("/{id}")
    public PersonOutputDTO updatePerson(@PathVariable("id") String id, @RequestBody PersonInputDTO personInputDTO) {
        return updatePersonPort.updatePerson(id, personInputDTO);
    }
}
