package com.bosonit.bs12.person.application.useCase;

import com.bosonit.bs12.exception.NotFoundException;
import com.bosonit.bs12.person.application.port.DeletePersonPort;
import com.bosonit.bs12.person.domain.Person;
import com.bosonit.bs12.person.infrastructure.repository.PersonRepository;

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
