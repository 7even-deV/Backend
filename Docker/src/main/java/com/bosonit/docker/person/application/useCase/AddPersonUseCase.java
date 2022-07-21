package com.bosonit.docker.person.application.useCase;

import com.bosonit.docker.person.application.port.AddPersonPort;
import com.bosonit.docker.person.domain.Person;
import com.bosonit.docker.person.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.docker.person.infrastructure.dto.output.PersonOutputDTO;
import com.bosonit.docker.person.infrastructure.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddPersonUseCase implements AddPersonPort {

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDTO addPerson(PersonInputDTO personInputDTO) {
        Person person = new Person(personInputDTO);
        personRepository.save(person);

        return new PersonOutputDTO(person);
    }
}
