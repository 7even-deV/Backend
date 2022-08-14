package com.bosonit.ejte.application.service;

import com.bosonit.ejte.domain.Person;
import com.bosonit.ejte.infrastructure.dto.PersonDTO;

import java.util.List;

public interface PersonService {
    Person addPerson(PersonDTO personDTO);
    List<Person> getPersons();
    Person getPersonId(Integer id) throws Exception;
    List<Person> getPersonName(String name);
    Person updatePerson(Integer id, Person person);
    void deletePerson(Integer id);
}
