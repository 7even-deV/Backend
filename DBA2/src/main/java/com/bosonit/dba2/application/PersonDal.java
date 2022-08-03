package com.bosonit.dba2.application;

import com.bosonit.dba2.domain.Person;

import java.util.List;

public interface PersonDal {
    Person addPerson(Person person);
    List<Person> getAll();
    Person findById(String id);
    List<Person> findByName(String name);
    Person updatePerson(String id, Person person);
    void deletePerson(Person person);
}
