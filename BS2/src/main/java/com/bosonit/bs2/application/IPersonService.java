package com.bosonit.bs2.application;

import com.bosonit.bs2.domain.Person;
import org.springframework.stereotype.Service;

@Service
public interface IPersonService {
    void setPerson(Person person);
    Person getPerson();
}
