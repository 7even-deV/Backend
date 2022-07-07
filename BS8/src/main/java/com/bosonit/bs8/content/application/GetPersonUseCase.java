package com.bosonit.bs8.content.application;

import com.bosonit.bs8.content.application.port.IGetPersonPort;
import com.bosonit.bs8.content.domain.Person;
import com.bosonit.bs8.content.infrastructure.controller.dto.output.PersonOutputDTO;
import com.bosonit.bs8.content.infrastructure.repository.IPersonRepository;

import com.bosonit.bs8.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetPersonUseCase implements IGetPersonPort {

    @Autowired
    IPersonRepository iPersonRepository;

    @Override
    public PersonOutputDTO getPersonId(int id) throws NotFoundException {
        Person person = iPersonRepository
                .findById(id).orElseThrow(
                        () -> new NotFoundException("No user exists with ID: " + id));
        return new PersonOutputDTO(person);
    }

    @Override
    public List<PersonOutputDTO> getPersonName(String name) {
        List<PersonOutputDTO> tempList = new ArrayList<>();
        iPersonRepository.findByName(name).forEach(person -> {
            tempList.add(new PersonOutputDTO(person));
        });
        return tempList;
    }

    @Override
    public List<PersonOutputDTO> getPersons() {
        List<PersonOutputDTO> tempList = new ArrayList<>();
        iPersonRepository.findAll().forEach(person -> {
            PersonOutputDTO personOutputDTO = new PersonOutputDTO(person);
            tempList.add(personOutputDTO);
        });
        return tempList;
    }
}
