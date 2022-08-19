package com.bosonit.ejs3.person.application.impl;

import com.bosonit.ejs3.person.application.service.PersonService;
import com.bosonit.ejs3.person.domain.Person;
import com.bosonit.ejs3.person.infrastructure.dto.PersonDTO;
import com.bosonit.ejs3.person.infrastructure.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Component
@Transactional
@Slf4j
public class PersonServiceImpl implements PersonService, UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("No person named " + username + " was found.");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        person.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new org.springframework.security.core.userdetails.User(person.getUsername(), person.getPassword(),
                authorities);
    }

    @Override
    public Person addPerson(PersonDTO personDTO) {
        personDTO.setPassword(passwordEncoder.encode(personDTO.getPassword()));
        return personRepository.save(new Person(personDTO));
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(String idPerson) {
        return personRepository.findById(idPerson).orElseThrow();
    }

    @Override
    public Person getPersonByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    @Override
    public Person updatePerson(String idPerson, PersonDTO personDTO) {
        Person personFound = personRepository.findById(idPerson).orElseThrow();

        if (personDTO.getUsername() != null) {
            personFound.setUsername(personDTO.getUsername());
        }
        if (personDTO.getPassword() != null) {
            personDTO.setPassword(passwordEncoder.encode(personDTO.getPassword()));
            personFound.setPassword(personDTO.getPassword());
        }
        if (personDTO.getName() != null) {
            personFound.setName(personDTO.getName());
        }
        if (personDTO.getSurname() != null) {
            personFound.setSurname(personDTO.getSurname());
        }
        if (personDTO.getAge() != null) {
            personFound.setAge(personDTO.getAge());
        }
        if (personDTO.getRoles() != null) {
            personFound.setRoles(personDTO.getRoles());
        }

        return personRepository.save(personFound);
    }

    @Override
    public void deletePerson(String idPerson) {
        personRepository.deleteById(idPerson);
    }
}
