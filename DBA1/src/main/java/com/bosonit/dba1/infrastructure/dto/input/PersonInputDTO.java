package com.bosonit.dba1.infrastructure.dto.input;

import lombok.Data;

import java.util.Date;

@Data
public class PersonInputDTO {

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
}
