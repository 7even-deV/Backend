package com.bosonit.ejte.application.service;

import com.bosonit.ejte.application.impl.PersonServiceImpl;
import com.bosonit.ejte.domain.Person;
import com.bosonit.ejte.infrastructure.dto.PersonDTO;
import com.bosonit.ejte.infrastructure.repository.PersonRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private Person person = new Person(new PersonDTO(1, "Seven-z01", "1234", "Sergio", "Fuentes", 35));

    private List<Person> persons = List.of(person);

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonServiceImpl personService;

    @BeforeEach
    void setUp() {
        personRepository.save(person);
    }

    @Test
    void addPersonTest() {
        PersonDTO personTestDTO = new PersonDTO(1, "Seven-z01", "1234", "Sergio", "Fuentes", 35);
        Person personTest = new Person(new PersonDTO(1, "Seven-z01", "1234", "Sergio", "Fuentes", 35));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(personTest);
        assertEquals(personTest, personService.addPerson(personTestDTO));
    }

    @Test
    void getAllPersonsTest() {
        when(personRepository.findAll()).thenReturn(persons);
        assertEquals(persons, personService.getPersons());
    }

    @Test
    void getPersonByIdTest() throws Exception {
        when(personRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(person));
        assertEquals(person, personService.getPersonId(1));
    }

    @Test
    void getPersonByNameTest() {
        when(personRepository.findByName(Mockito.any(String.class))).thenReturn(persons);
        assertEquals(persons, personService.getPersonName("Sergio"));
    }

    @Test
    void updatePersonTest() {
        when(personRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(person));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        assertEquals(person, personService.updatePerson(1, person));
    }

    @Test
    void updatePersonNullUsernameTest() {
        Person personTest = new Person(new PersonDTO(null, null, "1234", "Sergio", "Fuentes", 35));
        personRepository.save(personTest);
        when(personRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(person));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        assertEquals(person, personService.updatePerson(1, personTest));
    }

    @Test
    void updatePersonNullPasswordTest() {
        Person personTest = new Person(new PersonDTO(null, "Seven-z01", null, "Sergio", "Fuentes", 35));
        personRepository.save(personTest);
        when(personRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(person));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        assertEquals(person, personService.updatePerson(1, personTest));
    }

    @Test
    void updatePersonNullNameTest() {
        Person personTest = new Person(new PersonDTO(null, "Seven-z01", "1234", null, "Fuentes", 35));
        personRepository.save(personTest);
        when(personRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(person));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        assertEquals(person, personService.updatePerson(1, personTest));
    }

    @Test
    void updatePersonNullSurnameTest() {
        Person personTest = new Person(new PersonDTO(null, "Seven-z01", "1234", "Sergio", null, 35));
        personRepository.save(personTest);
        when(personRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(person));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        assertEquals(person, personService.updatePerson(1, personTest));
    }

    @Test
    void updatePersonNullAgeTest() {
        Person personTest = new Person(new PersonDTO(null, "Seven-z01", "1234", "Sergio", "Fuentes", null));
        personRepository.save(personTest);
        when(personRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(person));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        assertEquals(person, personService.updatePerson(1, personTest));
    }

    @Test
    void deletePersonByIdTest() {
        lenient().when(personRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(new Person()));
        personService.deletePerson(1);
        verify(personRepository).deleteById(Mockito.any(Integer.class));
    }
}
