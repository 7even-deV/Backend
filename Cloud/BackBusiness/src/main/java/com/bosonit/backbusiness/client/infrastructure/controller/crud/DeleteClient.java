package com.bosonit.backbusiness.client.infrastructure.controller.crud;

import com.bosonit.backbusiness.client.application.service.ClientService;
import com.bosonit.backbusiness.utils.exceptions.CustomUnprocessableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/business/client")
public class DeleteClient {

    @Autowired
    ClientService clientService;

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable UUID id) {
        if (clientService.filterClientByEmail("adminbus@business.local").getIdClient().equals(id)) {
            throw new CustomUnprocessableException("Unable to delete admin.");
        }

        clientService.deleteClient(id);
    }
}
