package com.bosonit.ejs3.it;

import com.bosonit.ejs3.role.domain.Role;
import com.bosonit.ejs3.role.infrastructure.dto.RoleDTO;
import com.bosonit.ejs3.role.infrastructure.repository.RoleRepository;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
@Tag("IntegrationTest")
@DisplayName("IntegrationTest - RoleControllerTest")
class RoleControllerTest {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        Role role = new Role(new RoleDTO(null, "VIP"));
        roleRepository.save(role);
    }

    private final String authorization = "Bearer " +
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTZXZlbi16MDEiLCJyb2xlcyI6WyJBRE1JTiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcGVyc29uL2xvZ2luIiwiZXhwIjoxNjYxMDEwODI2fQ.nYUEeNRuGdMsg3pF5T7HiyF07XfC9ELjGLliEZk63PA";

    @Test
    @DisplayName("addRoleTest")
    void addRoleTest() {
        RoleDTO roleDTOTest = new RoleDTO(null, "roleNameTest");
        RequestEntity<RoleDTO> requestEntity = RequestEntity
                .post("http://localhost:" + port + "/role")
                .header("Authorization", authorization)
                .body(roleDTOTest);
        ParameterizedTypeReference<String> message = new ParameterizedTypeReference<String>() {};
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, message);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
    }

    @Test
    @DisplayName("getAllRolesTest")
    void getAllRolesTest() throws URISyntaxException {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(new URI("http://localhost:" + port + "/role"))
                .header("Authorization", authorization)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        ParameterizedTypeReference<List<RoleDTO>> list = new ParameterizedTypeReference<List<RoleDTO>>() {};
        ResponseEntity<List<RoleDTO>> responseEntity = restTemplate.exchange(requestEntity, list);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("deleteRoleTest")
    void deleteRoleTest() {
        RequestEntity<Void> requestEntity = RequestEntity
                .delete("http://localhost:" + port + "/role/r-00003")
                .header("Authorization", authorization)
                .accept(MediaType.ALL)
                .build();
        ParameterizedTypeReference<String> message = new ParameterizedTypeReference<String>() {};
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, message);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
    }
}
