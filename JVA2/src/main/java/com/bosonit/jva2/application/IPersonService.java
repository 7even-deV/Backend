package com.bosonit.jva2.application;

import com.bosonit.jva2.domain.Person;
import org.springframework.stereotype.Service;

@Service
public interface IPersonService {
    void addPerson(Person person);
    Person getPerson();
}
