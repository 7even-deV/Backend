package com.bosonit.ejte.domain;

import com.bosonit.ejte.infrastructure.dto.PersonDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Person {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private Integer age;

    public Person(PersonDTO personDTO) {
        setId(personDTO.getId());
        setUsername(personDTO.getUsername());
        setPassword(personDTO.getPassword());
        setName(personDTO.getName());
        setSurname(personDTO.getSurname());
        setAge(personDTO.getAge());
    }
}
