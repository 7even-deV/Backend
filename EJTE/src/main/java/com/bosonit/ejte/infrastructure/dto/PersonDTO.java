package com.bosonit.ejte.infrastructure.dto;

import com.bosonit.ejte.domain.Person;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDTO {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private Integer age;

    public PersonDTO(Person person) {
        setId(person.getId());
        setUsername(person.getUsername());
        setPassword(person.getPassword());
        setName(person.getName());
        setSurname(person.getSurname());
        setAge(person.getAge());
    }

    public PersonDTO(Integer id, String username, String password, String name, String surname, Integer age) {
        setId(id);
        setUsername(username);
        setPassword(password);
        setName(name);
        setSurname(surname);
        setAge(age);
    }
}
