package com.bosonit.backbusiness.client.infrastructure.controller.crud;

import com.bosonit.backbusiness.client.application.service.ClientService;
import com.bosonit.backbusiness.client.infrastructure.controller.dto.output.ClientOutputDTO;
import com.bosonit.backbusiness.utils.auth.AuthUtils;
import com.bosonit.backbusiness.utils.exceptions.CustomUnprocessableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/business/client")
public class ReadClient {

    @Autowired
    ClientService clientService;

    @GetMapping
    public List<ClientOutputDTO> findAll() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientOutputDTO filterClientById(@PathVariable UUID id, @RequestHeader("Authorization") String auth) {
        UUID idToken = AuthUtils.getId(auth);
        if (!idToken.equals(id)) {
            throw new CustomUnprocessableException(
                    "The authenticated client ID does not correspond to the client you want to read.");
        }

        return clientService.filterClientById(id);
    }

    @GetMapping("/email/{email}")
    public ClientOutputDTO filterClientByEmail(@PathVariable String email) {
        return clientService.filterClientByEmail(email);
    }
}
