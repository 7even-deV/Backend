package com.bosonit.ejs3.person.infrastructure.dto;

import com.bosonit.ejs3.person.domain.Person;
import com.bosonit.ejs3.role.domain.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
public class PersonDTO {
    private String idPerson;
    private String username;
    private String password;
    private String name;
    private String surname;
    private Integer age;
    private Collection<Role> roles = new ArrayList<>();

    public PersonDTO(Person person) {
        setIdPerson(person.getIdPerson());
        setUsername(person.getUsername());
        setPassword(person.getPassword());
        setName(person.getName());
        setSurname(person.getSurname());
        setAge(person.getAge());
        setRoles(person.getRoles());
    }

    public PersonDTO(String idPerson, String username, String password, String name, String surname, Integer age, Collection<Role> roles) {
        setIdPerson(idPerson);
        setUsername(username);
        setPassword(password);
        setName(name);
        setSurname(surname);
        setAge(age);
        setRoles(roles);
    }
}
