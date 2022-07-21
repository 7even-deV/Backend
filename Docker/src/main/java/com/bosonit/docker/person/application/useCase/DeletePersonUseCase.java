package com.bosonit.docker.person.application.useCase;

import com.bosonit.docker.exception.NotFoundException;
import com.bosonit.docker.person.application.port.DeletePersonPort;
import com.bosonit.docker.person.domain.Person;
import com.bosonit.docker.person.infrastructure.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePersonUseCase implements DeletePersonPort {

    @Autowired
    PersonRepository personRepository;

    @Override
    public void deletePerson(String id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No username found with id: " + id));
        personRepository.delete(person);
    }
}
