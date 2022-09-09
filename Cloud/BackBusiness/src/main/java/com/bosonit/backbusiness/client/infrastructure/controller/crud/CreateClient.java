package com.bosonit.backbusiness.client.infrastructure.controller.crud;

import com.bosonit.backbusiness.client.application.service.ClientService;
import com.bosonit.backbusiness.client.infrastructure.controller.dto.output.ClientOutputDTO;
import com.bosonit.backbusiness.client.infrastructure.controller.dto.input.ClientInputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
