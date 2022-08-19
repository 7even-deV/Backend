package com.bosonit.ejs3.person.application.service;

import com.bosonit.ejs3.person.domain.Person;
import com.bosonit.ejs3.person.infrastructure.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    Person addPerson(PersonDTO personDTO);
    List<Person> getAllPersons();
    Person getPersonById(String idPerson) throws Exception;
    Person getPersonByUsername(String username);
    Person updatePerson(String idPerson, PersonDTO personDTO);
    void deletePerson(String idPerson);
}
