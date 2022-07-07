package com.bosonit.bs8.content.application;

import com.bosonit.bs8.content.application.port.IAddPersonPort;
import com.bosonit.bs8.content.domain.Person;
import com.bosonit.bs8.content.infrastructure.controller.dto.input.PersonInputDTO;
import com.bosonit.bs8.content.infrastructure.controller.dto.output.PersonOutputDTO;
import com.bosonit.bs8.content.infrastructure.repository.IPersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddPersonUseCase implements IAddPersonPort {

    @Autowired
    IPersonRepository iPersonRepository;

    @Override
    public PersonOutputDTO addPerson(PersonInputDTO personInputDTO){
        Person person = new Person(personInputDTO);
        iPersonRepository.save(person);
        return new PersonOutputDTO(person);
    }
}
