package com.bosonit.ejs3.unit;

import com.bosonit.ejs3.person.application.impl.PersonServiceImpl;
import com.bosonit.ejs3.person.domain.Person;
import com.bosonit.ejs3.person.infrastructure.dto.PersonDTO;

import com.bosonit.ejs3.person.infrastructure.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Tag("UnitTest")
@DisplayName("UnitTest - PersonServiceTest")
class PersonServiceTest {

    private final Person person = new Person(new PersonDTO(null, "Sergio_Fuentes", "myPassword", "Sergio", "Fuentes", 35, new ArrayList<>()));

    private final List<Person> persons = List.of(person);

    @Mock
    PersonRepository personRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    PersonServiceImpl personService;

    @BeforeEach
    void setUp() {
        personRepository.save(person);
    }

    @Test
    @DisplayName("addPersonTest")
    void addPersonTest() {
        Person personTest = new Person(new PersonDTO(null, "usernameTest", "passwordTest", "nameTest", "surnameTest", null, new ArrayList<>()));
        PersonDTO personDTOTest = new PersonDTO(null, "usernameTest", "passwordTest", "nameTest", "surnameTest", null, new ArrayList<>());
        personTest.setPassword(passwordEncoder.encode(personTest.getPassword()));
        personDTOTest.setPassword(passwordEncoder.encode(personDTOTest.getPassword()));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(personTest);
        assertEquals(personTest.getUsername(), personService.addPerson(personDTOTest).getUsername());
    }

    @Test
    @DisplayName("getAllPersonsTest")
    void getAllPersonsTest() {
        when(personRepository.findAll()).thenReturn(persons);
        assertEquals(persons, personService.getAllPersons());
    }

    @Test
    @DisplayName("getPersonByIdTest")
    void getPersonByIdTest() {
        when(personRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(person));
        assertEquals(person, personService.getPersonById("p-00003"));
    }

    @Test
    @DisplayName("getPersonByUsernameTest")
    void getPersonByUsernameTest() {
        when(personRepository.findByUsername(Mockito.any(String.class))).thenReturn(person);
        assertEquals(person, personService.getPersonByUsername("usernameTest"));
    }

    @Test
    @DisplayName("updatePersonTest")
    void updatePersonTest() {
        when(personRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(person));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        assertEquals(person, personService.updatePerson("p-00003", new PersonDTO(person)));
    }

    @Test
    @DisplayName("updatePersonNullUsernameTest")
    void updatePersonNullUsernameTest() {
        PersonDTO personDTOTest = new PersonDTO(null, null, null, "Sergio", "Fuentes", 35, new ArrayList<>());
        personRepository.save(new Person(personDTOTest));
        when(personRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(person));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        assertEquals(person, personService.updatePerson("p-00003", personDTOTest));
    }

    @Test
    @DisplayName("updatePersonNullPasswordTest")
    void updatePersonNullPasswordTest() {
        PersonDTO personDTOTest = new PersonDTO(null, "Seven-z01", null, "Sergio", "Fuentes", 35, new ArrayList<>());
        personRepository.save(new Person(personDTOTest));
        when(personRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(person));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        assertEquals(person, personService.updatePerson("p-00003", personDTOTest));
    }

    @Test
    @DisplayName("updatePersonNullNameTest")
    void updatePersonNullNameTest() {
        PersonDTO personDTOTest = new PersonDTO(null, "Seven-z01", null, null, "Fuentes", 35, new ArrayList<>());
        personRepository.save(new Person(personDTOTest));
        when(personRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(person));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        assertEquals(person, personService.updatePerson("p-00003", personDTOTest));
    }

    @Test
    @DisplayName("updatePersonNullSurnameTest")
    void updatePersonNullSurnameTest() {
        PersonDTO personDTOTest = new PersonDTO(null, "Seven-z01", null, "Sergio", null, 35, new ArrayList<>());
        personRepository.save(new Person(personDTOTest));
        when(personRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(person));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        assertEquals(person, personService.updatePerson("p-00003", personDTOTest));
    }

    @Test
    @DisplayName("updatePersonNullAgeTest")
    void updatePersonNullAgeTest() {
        PersonDTO personDTOTest = new PersonDTO(null, "Seven-z01", null, "Sergio", "Fuentes", null, new ArrayList<>());
        personRepository.save(new Person(personDTOTest));
        when(personRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(person));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        assertEquals(person, personService.updatePerson("p-00003", personDTOTest));
    }

    @Test
    @DisplayName("updatePersonNullRolesTest")
    void updatePersonNullRolesTest() {
        PersonDTO personDTOTest = new PersonDTO(null, "Seven-z01", null, "Sergio", "Fuentes", 35, null);
        personRepository.save(new Person(personDTOTest));
        when(personRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(person));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        assertEquals(person, personService.updatePerson("p-00003", personDTOTest));
    }

    @Test
    @DisplayName("deletePersonByIdTest")
    void deletePersonByIdTest() {
        lenient().when(personRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(new Person()));
        personService.deletePerson("p-00003");
        verify(personRepository).deleteById(Mockito.any(String.class));
    }
}
