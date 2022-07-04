package com.bosonit.rs1.application.interfaces;

import com.bosonit.rs1.domain.Person;

public interface IPersonService {
    void addPerson(Person person);
    Person findPerson(int id);
    Person findPerson(String name);
    boolean removePerson(int id);
}
