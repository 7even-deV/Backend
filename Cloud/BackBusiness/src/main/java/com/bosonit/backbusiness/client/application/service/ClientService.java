package com.bosonit.backbusiness.client.application.service;

import com.bosonit.backbusiness.client.infrastructure.controller.dto.input.ClientInputDTO;
import com.bosonit.backbusiness.client.infrastructure.controller.dto.output.ClientOutputDTO;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    ClientOutputDTO addClient(ClientInputDTO clientInputDTO);
    List<ClientOutputDTO> getAllClients();
    ClientOutputDTO filterClientById(UUID id);
    ClientOutputDTO filterClientByEmail(String email);
    ClientOutputDTO updateClient(UUID id, ClientInputDTO clientInputDTO);
    void deleteClient(UUID id);
}
