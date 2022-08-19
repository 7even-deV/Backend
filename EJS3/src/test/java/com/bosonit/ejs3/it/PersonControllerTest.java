package com.bosonit.ejs3.it;

import com.bosonit.ejs3.person.domain.Person;
import com.bosonit.ejs3.person.infrastructure.dto.PersonDTO;
import com.bosonit.ejs3.person.infrastructure.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
@Tag("IntegrationTest")
@DisplayName("IntegrationTest - PersonControllerTest")
class PersonControllerTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        Person person = new Person(new PersonDTO(null, "Sergio_Fuentes", "myPassword", "Sergio", "Fuentes", 35, new ArrayList<>()));
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }

    private final String authorization = "Bearer " +
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTZXZlbi16MDEiLCJyb2xlcyI6WyJBRE1JTiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcGVyc29uL2xvZ2luIiwiZXhwIjoxNjYxMDEwODI2fQ.nYUEeNRuGdMsg3pF5T7HiyF07XfC9ELjGLliEZk63PA";

    @Test
    @DisplayName("addPersonTest")
    void addPersonTest() {
        PersonDTO personDTOTest = new PersonDTO(null, "usernameTest", "passwordTest", "nameTest", "surnameTest", null, new ArrayList<>());
        personDTOTest.setPassword(passwordEncoder.encode(personDTOTest.getPassword()));
        RequestEntity<PersonDTO> requestEntity = RequestEntity
                .post("http://localhost:" + port + "/person")
                .header("Authorization", authorization)
                .body(personDTOTest);
        ParameterizedTypeReference<String> message = new ParameterizedTypeReference<String>() {};
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, message);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
    }

    @Test
    @DisplayName("getAllPersonsTest")
    void getAllPersonsTest() throws URISyntaxException {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(new URI("http://localhost:" + port + "/person"))
                .header("Authorization", authorization)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        ParameterizedTypeReference<List<PersonDTO>> list = new ParameterizedTypeReference<List<PersonDTO>>() {};
        ResponseEntity<List<PersonDTO>> responseEntity = restTemplate.exchange(requestEntity, list);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("getPersonIdTest")
    void getPersonIdTest() throws URISyntaxException {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(new URI("http://localhost:" + port + "/person/p-00002"))
                .header("Authorization", authorization)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        ParameterizedTypeReference<Person> list = new ParameterizedTypeReference<Person>() {};
        ResponseEntity<Person> responseEntity = restTemplate.exchange(requestEntity, list);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(responseEntity.getBody().getIdPerson()).isEqualTo("p-00002");
    }

    @Test
    @DisplayName("getPersonUsernameTest")
    void getPersonUsernameTest() throws URISyntaxException {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(new URI("http://localhost:" + port + "/person/username/usernameTest"))
                .header("Authorization", authorization)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        ParameterizedTypeReference<PersonDTO> list = new ParameterizedTypeReference<PersonDTO>() {};
        ResponseEntity<PersonDTO> responseEntity = restTemplate.exchange(requestEntity, list);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(responseEntity.getBody().getUsername()).isEqualTo("usernameTest");
    }

    @Test
    @DisplayName("updatePersonTest")
    void updatePersonTest() {
        PersonDTO personDTOTest = new PersonDTO(null, "Seven-z01", "newPasswordTest", "Sergio", "Fuentes Moya", 36, new ArrayList<>());
        personDTOTest.setPassword(passwordEncoder.encode(personDTOTest.getPassword()));
        RequestEntity<PersonDTO> requestEntity = RequestEntity
                .put("http://localhost:" + port + "/person/p-00001")
                .header("Authorization", authorization)
                .body(personDTOTest);
        ParameterizedTypeReference<String> message = new ParameterizedTypeReference<String>() {};
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, message);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
    }

    @Test
    @DisplayName("deletePersonTest")
    void deletePersonTest() {
        RequestEntity<Void> requestEntity = RequestEntity
                .delete("http://localhost:" + port + "/person/p-00003")
                .header("Authorization", authorization)
                .accept(MediaType.ALL)
                .build();
        ParameterizedTypeReference<String> message = new ParameterizedTypeReference<String>() {};
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, message);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
    }
}
