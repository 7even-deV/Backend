package com.bosonit.dba1.infrastructure.dto.output;

import com.bosonit.dba1.domain.Person;

import lombok.Data;

import java.util.Date;

@Data
public class PersonOutputDTO {

    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private boolean active;
    private Date created_date;
    private String image_url;
    private Date termination_date;

    public PersonOutputDTO(Person person) {

        setId(person.getId());
        setUsername(person.getUsername());
        setPassword(person.getPassword());
        setName(person.getName());
        setSurname(person.getSurname());
        setCompany_email(person.getCompany_email());
        setPersonal_email(person.getPersonal_email());
        setCity(person.getCity());
        setActive(person.isActive());
        setCreated_date(person.getCreated_date());
        setImage_url(person.getImage_url());
        setTermination_date(person.getTermination_date());
    }
}
