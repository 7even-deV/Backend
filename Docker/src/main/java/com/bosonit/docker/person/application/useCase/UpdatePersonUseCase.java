package com.bosonit.docker.person.application.useCase;

import com.bosonit.docker.exception.NotFoundException;
import com.bosonit.docker.person.application.port.UpdatePersonPort;
import com.bosonit.docker.person.domain.Person;
import com.bosonit.docker.person.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.docker.person.infrastructure.dto.output.PersonOutputDTO;
import com.bosonit.docker.person.infrastructure.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePersonUseCase implements UpdatePersonPort {

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDTO updatePerson(String id, PersonInputDTO personInputDto) throws NotFoundException {
        Person foundPerson = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No username found with id: " + id));
        foundPerson.update(personInputDto);
        personRepository.save(foundPerson);

        return new PersonOutputDTO(foundPerson);
    }
}
