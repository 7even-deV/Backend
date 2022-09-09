package com.bosonit.backweb.client.infrastructure.controller.crud;

import com.bosonit.backweb.client.application.service.ClientService;

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

    // @DeleteMapping("/{id}")
    // public void deleteTrip(@PathVariable UUID id) {
    //     clientService.deleteClient(id);
    // }
}
