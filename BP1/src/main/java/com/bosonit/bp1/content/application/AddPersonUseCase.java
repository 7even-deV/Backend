package com.bosonit.bp1.content.application;

import com.bosonit.bp1.content.application.port.IAddPersonPort;
import com.bosonit.bp1.content.domain.Person;
import com.bosonit.bp1.content.infrastructure.controller.dto.input.PersonInputDTO;
import com.bosonit.bp1.content.infrastructure.controller.dto.output.PersonOutputDTO;
import com.bosonit.bp1.content.infrastructure.repository.IPersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddPersonUseCase implements IAddPersonPort {

    @Autowired
    IPersonRepository iPersonRepository;

    @Override
    public PersonOutputDTO addPerson(PersonInputDTO personInputDTO) throws Exception {
        Person person = new Person(personInputDTO);
        iPersonRepository.save(person);
        return new PersonOutputDTO(person);
    }
}
