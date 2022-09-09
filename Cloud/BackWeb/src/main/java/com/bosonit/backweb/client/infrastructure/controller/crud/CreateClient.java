package com.bosonit.backweb.client.infrastructure.controller.crud;

import com.bosonit.backweb.client.application.service.ClientService;
import com.bosonit.backweb.client.infrastructure.controller.dto.input.ClientInputDTO;
import com.bosonit.backweb.client.infrastructure.controller.dto.output.ClientOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business/client")
public class CreateClient {

    @Autowired
    ClientService clientService;

    @PostMapping
    public ClientOutputDTO addClient(@RequestBody ClientInputDTO clientInputDTO) {
        return clientService.addClient(clientInputDTO);
    }
}
