package com.bosonit.dba2.domain;

import com.bosonit.dba2.infrastructure.dto.PersonDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Person {
    @Id
    private String id;
    private String username;
    private String password;
    private String name;
    private String surname;

    public Person(PersonDTO personDTO) {
        setId(personDTO.getId());
        setUsername(personDTO.getUsername());
        setPassword(personDTO.getPassword());
        setName(personDTO.getName());
        setSurname(personDTO.getSurname());
    }
}
