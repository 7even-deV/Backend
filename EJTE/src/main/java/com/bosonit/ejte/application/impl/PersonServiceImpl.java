package com.bosonit.ejte.application.impl;

import com.bosonit.ejte.application.service.PersonService;
import com.bosonit.ejte.domain.Person;
import com.bosonit.ejte.infrastructure.dto.PersonDTO;
import com.bosonit.ejte.infrastructure.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person addPerson(PersonDTO personDTO) {
        return personRepository.save(new Person(personDTO));
    }

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonId(Integer id) throws Exception {
        return personRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Person> getPersonName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    public Person updatePerson(Integer id, Person person) {
        Person personFound = personRepository.findById(id).orElseThrow();

        if (person.getUsername() != null) {
            personFound.setUsername(person.getUsername());
        }
        if (person.getPassword() != null) {
            personFound.setPassword(person.getPassword());
        }
        if (person.getName() != null) {
            personFound.setName(person.getName());
        }
        if (person.getSurname() != null) {
            personFound.setSurname(person.getSurname());
        }
        if (person.getAge() != null) {
            personFound.setAge(person.getAge());
        }

        return personRepository.save(personFound);
    }

    @Override
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }
}
