package com.bosonit.bs2.application;

import com.bosonit.bs2.domain.Person;

// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// @Component
@Service
public class PersonServiceImpl implements IPersonService {
    private Person person;

    @Override
    public void setPerson(Person person) {
        this.person = Person.builder()
                .name(person.getName())
                .age(person.getAge())
                .city(person.getCity())
                .build();
    }

    @Override
    public Person getPerson() {
        return person;
    }
}
