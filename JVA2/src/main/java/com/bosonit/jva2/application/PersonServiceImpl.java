package com.bosonit.jva2.application;

import com.bosonit.jva2.domain.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonServiceImpl implements IPersonService {

    private Person person;

    @Override
    public void addPerson(Person person) {
        this.person = person;
    }

    @Override
    public Person getPerson() {
        return person;
    }
}
