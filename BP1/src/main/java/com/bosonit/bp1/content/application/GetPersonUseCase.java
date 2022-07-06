package com.bosonit.bp1.content.application;

import com.bosonit.bp1.content.application.port.IGetPersonPort;
import com.bosonit.bp1.content.domain.Person;
import com.bosonit.bp1.content.infrastructure.controller.dto.output.PersonOutputDTO;
import com.bosonit.bp1.content.infrastructure.repository.IPersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetPersonUseCase implements IGetPersonPort {

    @Autowired
    IPersonRepository iPersonRepository;

    @Override
    public PersonOutputDTO getPersonId(int id) throws Exception {
        Person person = iPersonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user exists with ID: " + id));
        return new PersonOutputDTO(person);
    }

    @Override
    public List<PersonOutputDTO> getPersonName(String username) throws Exception {
        List<PersonOutputDTO> tempList = new ArrayList<>();
        iPersonRepository.findByName(username).forEach(person -> {
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
