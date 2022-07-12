package com.bosonit.bs9.person.domain;

import com.bosonit.bs9.PersonSequenceIdGenerator;
import com.bosonit.bs9.person.infrastructure.dto.input.PersonInputDTO;

import com.bosonit.bs9.student.domain.Student;
import com.bosonit.bs9.teacher.domain.Teacher;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idPerson")
    @GenericGenerator(name = "idPerson", strategy = "com.bosonit.bs9.PersonSequenceIdGenerator", parameters = {
            @Parameter(name = PersonSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = PersonSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "p"),
            @Parameter(name = PersonSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
    })
    private String idPerson;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String companyEmail;

    @Column
    private String personalEmail;

    @Column
    private String city;

    @Column
    private boolean active;

    @Column
    private Date createdDate;

    @Column
    private String imageUrl;

    @Column
    private Date terminationDate;

    @OneToOne(mappedBy = "person")
    private Student student;

    @OneToOne(mappedBy = "person")
    private Teacher teacher;

    public Person(PersonInputDTO personInputDTO) {
        setUsername(personInputDTO.getUsername());
        setPassword(personInputDTO.getPassword());
        setName(personInputDTO.getName());
        setSurname(personInputDTO.getSurname());
        setCompanyEmail(personInputDTO.getCompanyEmail());
        setPersonalEmail(personInputDTO.getPersonalEmail());
        setCity(personInputDTO.getCity());
        setCreatedDate(new Date());
        setActive(true);
        setImageUrl(personInputDTO.getImagenUrl());
    }

    public void update(PersonInputDTO personInputDTO) {
        if (personInputDTO.getUsername() != null
                && personInputDTO.getUsername().length() >= 6
                && personInputDTO.getUsername().length() <= 10) {
            setUsername(personInputDTO.getUsername());
        }
        if (personInputDTO.getName() != null) {
            setName(personInputDTO.getName());
        }
        if (personInputDTO.getSurname() != null) {
            setSurname(personInputDTO.getSurname());
        }
        if (personInputDTO.getPassword() != null) {
            setPassword(personInputDTO.getPassword());
        }
        if (personInputDTO.getCompanyEmail() != null) {
            setCompanyEmail(personInputDTO.getCompanyEmail());
        }
        if (personInputDTO.getPersonalEmail() != null) {
            setPersonalEmail(personInputDTO.getPersonalEmail());
        }
        if (personInputDTO.getCity() != null) {
            setCity(personInputDTO.getCity());
        }
        if (personInputDTO.getImagenUrl() != null) {
            setImageUrl(personInputDTO.getImagenUrl());
        }
    }
}
