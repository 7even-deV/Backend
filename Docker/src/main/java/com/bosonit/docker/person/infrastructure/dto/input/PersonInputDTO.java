package com.bosonit.docker.person.infrastructure.dto.input;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class PersonInputDTO {

    private String idPerson;

    @NotEmpty(message = "The username field cannot be empty.")
    @Size(min = 6, max = 10, message = "Username must be between 6 to 10 characters.")
    private String username;

    @NotEmpty(message = "The password field cannot be empty.")
    private String password;

    @NotEmpty(message = "The name field cannot be empty.")
    private String name;

    private String surname;

    @NotEmpty(message = "The company email field cannot be empty.")
    @Email(message = "Company email is invalid.")
    private String companyEmail;

    @NotEmpty(message = "The personal email field cannot be empty.")
    @Email(message = "Personal email is invalid.")
    private String personalEmail;

    @NotEmpty(message = "The city field cannot be empty.")
    private String city;

    private boolean active;

    private Date createDate;

    private String imagenUrl;

    private Date terminationDate;
}
