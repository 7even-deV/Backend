package com.bosonit.rs1.application.impls;

import com.bosonit.rs1.application.interfaces.IPersonService;
import com.bosonit.rs1.domain.Person;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {
    private List<Person> personList;

    PersonServiceImpl() {
        this.personList = new ArrayList<>();
    }

    @Override
    public void addPerson(Person person) {
        person.setId(personList.size());
        personList.add(person);
    }

    @Override
    public Person findPerson(int id) {
        return personList
                .stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Person findPerson(String name) {
        return personList
                .stream()
                .filter(person -> person.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean removePerson(int id) {
        Person foundPerson = findPerson(id);
        return personList.remove(foundPerson);
    }
}
