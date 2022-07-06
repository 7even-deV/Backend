package com.bosonit.bp1.content.application;

import com.bosonit.bp1.content.application.port.IUpdatePersonPort;
import com.bosonit.bp1.content.infrastructure.controller.dto.input.PersonInputDTO;
import com.bosonit.bp1.content.domain.Person;
import com.bosonit.bp1.content.infrastructure.controller.dto.output.PersonOutputDTO;
import com.bosonit.bp1.content.infrastructure.repository.IPersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdatePersonUseCase implements IUpdatePersonPort {

    @Autowired
    IPersonRepository iPersonRepository;

    @Override
    public PersonOutputDTO updatePerson(int id, PersonInputDTO personInputDTO) throws Exception {
        Person foundPerson = iPersonRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "No user found with ID: " + id));
        foundPerson.update(personInputDTO);
        iPersonRepository.save(foundPerson);
        return new PersonOutputDTO(foundPerson);
    }
}
