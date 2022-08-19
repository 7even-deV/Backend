package com.bosonit.ejs3;

import com.bosonit.ejs3.person.application.service.PersonService;
import com.bosonit.ejs3.person.infrastructure.dto.PersonDTO;
import com.bosonit.ejs3.role.application.service.RoleService;
import com.bosonit.ejs3.role.infrastructure.dto.RoleDTO;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class ApplicationEJS3 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationEJS3.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(PersonService personService, RoleService roleService) {
        return args -> {

            personService.addPerson(new PersonDTO(null, "Seven-z01", "1234", "Sergio", "Fuentes", 35, new ArrayList<>()));
            personService.addPerson(new PersonDTO(null, "username2", "password2", "name2", "surname2", null, new ArrayList<>()));

            roleService.addRole(new RoleDTO(null, "ADMIN"));
            roleService.addRole(new RoleDTO(null, "USER"));

            roleService.addRoleToPerson("Seven-z01", "ADMIN");
            roleService.addRoleToPerson("username2", "USER");
        };
    }
}
