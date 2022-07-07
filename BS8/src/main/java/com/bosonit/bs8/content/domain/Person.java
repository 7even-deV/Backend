package com.bosonit.bs8.content.domain;

import com.bosonit.bs8.content.infrastructure.controller.dto.input.PersonInputDTO;
import lombok.*;
import org.jetbrains.annotations.NotNull;

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

    public Person(@NotNull PersonInputDTO personInputDTO) {
        setUsername(personInputDTO.getUsername());
        setPassword(personInputDTO.getPassword());
        setName(personInputDTO.getName());
        setSurname(personInputDTO.getSurname());
        setCompany_email(personInputDTO.getCompany_email());
        setPersonal_email(personInputDTO.getPersonal_email());
        setCity(personInputDTO.getCity());
        setActive(true);
        setCreated_date(new Date());
        setImage_url(personInputDTO.getImage_url());
    }

    public void update(@NotNull PersonInputDTO personInputDTO) {
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
