package com.bosonit.backweb.client.infrastructure.controller.crud;

import com.bosonit.backweb.client.application.service.ClientService;
import com.bosonit.backweb.client.infrastructure.controller.dto.output.ClientOutputDTO;
import com.bosonit.backweb.utils.auth.AuthUtils;
import com.bosonit.backweb.utils.exceptions.CustomUnprocessableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/business/client")
public class ReadClient {

    @Autowired
    ClientService clientService;

    @Value("${urlbusiness}")
    String BUSINESS;

    // @GetMapping
    // public List<ClientOutputDTO> findAll() {
    //     return clientService.getAllClients();
    // }

    @GetMapping("/{id}")
    public ClientOutputDTO filterClientById(@PathVariable UUID id, @RequestHeader("Authorization") String auth) {
        HttpEntity<Object> request = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Void> re = new RestTemplate().exchange(BUSINESS + "/" + auth, HttpMethod.GET, request,
                Void.class);

        if (re.getStatusCode() == HttpStatus.OK) {
            UUID idToken = AuthUtils.getId(auth);
            if (!idToken.equals(id)) {
                throw new CustomUnprocessableException(
                        "The authenticated person does not correspond to the person you want to read.");
            }
            return clientService.filterClientById(id);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong Authentication!");
    }

    // @GetMapping("/email/{email}")
    // public ClientOutputDTO filterClientByEmail(@PathVariable String email) {
    //     return clientService.filterClientByEmail(email);
    // }
}
