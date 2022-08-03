package com.bosonit.dba2.infrastructure.dto;

import com.bosonit.dba2.domain.Person;
import lombok.Data;

@Data
public class PersonDTO {

    private String id;
    private String username;
    private String password;
    private String name;
    private String surname;

    public PersonDTO(Person person) {
        setId(person.getId());
        setUsername(person.getUsername());
        setPassword(person.getPassword());
        setName(person.getName());
        setSurname(person.getSurname());
    }

    public PersonDTO(String username, String password, String name, String surname) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setSurname(surname);
    }
}
