package com.bosonit.bs8.content.application;

import com.bosonit.bs8.content.application.port.IDeletePersonPort;
import com.bosonit.bs8.content.domain.Person;
import com.bosonit.bs8.content.infrastructure.repository.IPersonRepository;

import com.bosonit.bs8.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePersonUseCase implements IDeletePersonPort {

    @Autowired
    IPersonRepository iPersonRepository;

    @Override
    public void deletePerson(int id) {
        Person person = iPersonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No user found with ID: " + id));
        iPersonRepository.delete(person);
    }
}
