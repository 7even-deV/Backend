package com.bosonit.bs8.content.application;

import com.bosonit.bs8.content.application.port.IUpdatePersonPort;
import com.bosonit.bs8.content.infrastructure.controller.dto.input.PersonInputDTO;
import com.bosonit.bs8.content.domain.Person;
import com.bosonit.bs8.content.infrastructure.controller.dto.output.PersonOutputDTO;
import com.bosonit.bs8.content.infrastructure.repository.IPersonRepository;

import com.bosonit.bs8.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePersonUseCase implements IUpdatePersonPort {

    @Autowired
    IPersonRepository iPersonRepository;

    @Override
    public PersonOutputDTO updatePerson(int id, PersonInputDTO personInputDTO) throws NotFoundException {
        Person foundPerson = iPersonRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No user found with ID: " + id));
        foundPerson.update(personInputDTO);
        iPersonRepository.save(foundPerson);
        return new PersonOutputDTO(foundPerson);
    }
}
