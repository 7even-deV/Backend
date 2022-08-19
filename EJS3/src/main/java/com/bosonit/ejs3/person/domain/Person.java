package com.bosonit.ejs3.person.domain;

import com.bosonit.ejs3.SequenceIdGenerator;
import com.bosonit.ejs3.person.infrastructure.dto.PersonDTO;
import com.bosonit.ejs3.role.domain.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity(name = "Persons")
@Table(name = "Persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idPerson")
    @GenericGenerator(name = "idPerson", strategy = "com.bosonit.ejs3.SequenceIdGenerator", parameters = {
            @Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "p"),
            @Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
    })
    private String idPerson;
    private String username;
    private String password;
    private String name;
    private String surname;
    private Integer age;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    public Person(PersonDTO personDTO) {
        setIdPerson(personDTO.getIdPerson());
        setUsername(personDTO.getUsername());
        setPassword(personDTO.getPassword());
        setName(personDTO.getName());
        setSurname(personDTO.getSurname());
        setAge(personDTO.getAge());
        setRoles(personDTO.getRoles());
    }
}
