package com.bosonit.bs8.content.infrastructure.controller.dto.input;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class PersonInputDTO {

    private int id;

    @NotEmpty(message = "The username field cannot be empty.")
    @Size(min = 6, max = 10, message = "The username field must be between 6 and 10 characters.")
    private String username;

    @NotEmpty(message = "The password field cannot be empty.")
    private String password;

    @NotEmpty(message = "The name field cannot be empty.")
    private String name;

    private String surname;

    @NotEmpty(message = "The company_email field cannot be empty.")
    @Email(message = "Invalid email!")
    private String company_email;

    @NotEmpty(message = "The personal_email field cannot be empty.")
    @Email(message = "Invalid email!")
    private String personal_email;

    @NotEmpty(message = "The city field cannot be empty.")
    private String city;

    private boolean active;

    private Date created_date;

    private String image_url;

    private Date termination_date;
}
