package com.bosonit.bp1.content.domain;

import com.bosonit.bp1.content.infrastructure.controller.dto.input.PersonInputDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Id")
    private int id;

    @Column(name = "Username", nullable = true)
    private String username;

    @Column(name = "Password", nullable = true)
    private String password;

    @Column(name = "Name", nullable = true)
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Company_email", nullable = true)
    private String company_email;

    @Column(name = "Personal_email", nullable = true)
    private String personal_email;

    @Column(name = "City", nullable = true)
    private String city;

    @Column(name = "Active", nullable = true)
    private boolean active;

    @Column(name = "Created_date", nullable = true)
    private Date created_date;

    @Column(name = "Image_url")
    private String image_url;

    @Column(name = "Termination_date")
    private Date termination_date;

    public Person(PersonInputDTO personInputDTO) throws Exception {
        if (personInputDTO.getUsername() != null
                && personInputDTO.getUsername().length() >= 6
                && personInputDTO.getUsername().length() <= 10) {
            setUsername(personInputDTO.getUsername());
        } else {
            throw new Exception("The username field must be between 6 and 10 characters.");
        }
        if (personInputDTO.getName() != null) {
            setName(personInputDTO.getName());
        } else {
            throw new Exception("The name field cannot be empty.");
        }
        if (personInputDTO.getPassword() != null) {
            setPassword(personInputDTO.getPassword());
        } else {
            throw new Exception("The password field cannot be empty.");
        }
        if (personInputDTO.getCompany_email() != null) {
            setCompany_email(personInputDTO.getCompany_email());
        } else {
            throw new Exception("The company_email field cannot be empty.");
        }
        if (personInputDTO.getPersonal_email() != null) {
            setPersonal_email(personInputDTO.getPersonal_email());
        } else {
            throw new Exception("The personal_email field cannot be empty.");
        }
        if (personInputDTO.getCity() != null) {
            setCity(personInputDTO.getCity());
        } else {
            throw new Exception("The city field cannot be empty.");
        }
        setSurname(personInputDTO.getSurname());
        setActive(true);
        setCreated_date(new Date());
        setImage_url(personInputDTO.getImage_url());
    }

    public void update(PersonInputDTO personInputDTO) throws Exception {
        if (personInputDTO.getUsername() != null
                && personInputDTO.getUsername().length() >= 6
                && personInputDTO.getUsername().length() <= 10) {
            setUsername(personInputDTO.getUsername());
        }
        if (personInputDTO.getPassword() != null) {
            setPassword(personInputDTO.getPassword());
        }
        if (personInputDTO.getName() != null) {
            setName(personInputDTO.getName());
        }
        if (personInputDTO.getSurname() != null) {
            setSurname(personInputDTO.getSurname());
        }
        if (personInputDTO.getCompany_email() != null) {
            setCompany_email(personInputDTO.getCompany_email());
        }
        if (personInputDTO.getPersonal_email() != null) {
            setPersonal_email(personInputDTO.getPersonal_email());
        }
        if (personInputDTO.getCity() != null) {
            setCity(personInputDTO.getCity());
        }
        if (personInputDTO.getImage_url() != null) {
            setImage_url(personInputDTO.getImage_url());
        }
    }
}
