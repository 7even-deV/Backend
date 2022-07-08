package com.bosonit.ej3.person.application.useCase;

import com.bosonit.ej3.exception.NotFoundException;
import com.bosonit.ej3.person.application.port.GetPersonPort;
import com.bosonit.ej3.person.domain.Person;
import com.bosonit.ej3.person.infrastructure.dto.output.PersonOutputDTO;
import com.bosonit.ej3.person.infrastructure.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetPersonUseCase implements GetPersonPort {

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDTO getPersonId(String id) throws NotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No username found with id: " + id));

        return new PersonOutputDTO(person);
    }

    @Override
    public List<PersonOutputDTO> getPersonName(String username) {
        List<PersonOutputDTO> tempList = new ArrayList<>();
        personRepository.findByName(username).forEach(person -> {
            tempList.add(new PersonOutputDTO(person));
        });

        return tempList;
    }

    @Override
    public List<PersonOutputDTO> getPersons() {
        List<PersonOutputDTO> tempList = new ArrayList<>();
        personRepository.findAll().forEach(person -> {
            PersonOutputDTO personOutputDto = new PersonOutputDTO(person);
            tempList.add(personOutputDto);
        });

        return tempList;
    }
}
