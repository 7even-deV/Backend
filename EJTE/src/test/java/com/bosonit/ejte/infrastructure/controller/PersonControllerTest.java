package com.bosonit.ejte.infrastructure.controller;

import com.bosonit.ejte.domain.Person;
import com.bosonit.ejte.infrastructure.dto.PersonDTO;
import com.bosonit.ejte.infrastructure.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class PersonControllerTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        Person person = new Person(new PersonDTO(1, "Seven-z01", "1234", "Sergio", "Fuentes", 35));
        personRepository.save(person);
    }

    @Test
    void addPersonTest() {
        PersonDTO personDTOTest = new PersonDTO(2, "Seven-z01", "1234", "Sergio", "Fuentes", 35);
        ResponseEntity<PersonDTO> responseEntity = new RestTemplate()
                .postForEntity("http://localhost:" + port + "/person", personDTOTest, PersonDTO.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
    }

    @Test
    void getAllTest() throws URISyntaxException {
        RequestEntity<Void> requestEntity = RequestEntity.get(new URI("http://localhost:" + port + "/person"))
                .accept(MediaType.APPLICATION_JSON).build();
        ParameterizedTypeReference<List<PersonDTO>> list = new ParameterizedTypeReference<List<PersonDTO>>() {
        };
        ResponseEntity<List<PersonDTO>> responseEntity = restTemplate.exchange(requestEntity, list);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(1);
    }

    @Test
    void getPersonIdTest() throws URISyntaxException {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(new URI("http://localhost:" + port + "/person/" + 1)).accept(MediaType.APPLICATION_JSON)
                .build();
        ParameterizedTypeReference<Person> list = new ParameterizedTypeReference<Person>() {
        };
        ResponseEntity<Person> responseEntity = restTemplate.exchange(requestEntity, list);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(responseEntity.getBody().getId()).isEqualTo(1);
    }

    @Test
    void getPersonNameTest() throws URISyntaxException {
        RequestEntity<Void> requestEntity = RequestEntity.get(new URI("http://localhost:" + port + "/person/name/Sergio"))
                .accept(MediaType.APPLICATION_JSON).build();
        ParameterizedTypeReference<List<PersonDTO>> list = new ParameterizedTypeReference<List<PersonDTO>>() {
        };
        ResponseEntity<List<PersonDTO>> responseEntity = restTemplate.exchange(requestEntity, list);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(2);
    }

    @Test
    void updatePersonTest() {
        PersonDTO personDTOTest = new PersonDTO(null, "Seven-z01", "1234", "Sergio", "Fuentes Moya", 36);
        RequestEntity<PersonDTO> requestEntity = RequestEntity.put("http://localhost:" + port + "/person/" + 1)
                .body(personDTOTest);
        ParameterizedTypeReference<String> message = new ParameterizedTypeReference<String>() {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, message);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
    }

    @Test
    void deletePersonTest() {
        RequestEntity<Void> requestEntity = RequestEntity.delete("http://localhost:" + port + "/person/" + 1)
                .accept(MediaType.ALL).build();
        ParameterizedTypeReference<String> message = new ParameterizedTypeReference<String>() {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, message);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
    }
}
