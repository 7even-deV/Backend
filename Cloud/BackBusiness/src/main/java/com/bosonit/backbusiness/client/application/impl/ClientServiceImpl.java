package com.bosonit.backbusiness.client.application.impl;

import com.bosonit.backbusiness.client.application.service.ClientService;
import com.bosonit.backbusiness.client.domain.Client;
import com.bosonit.backbusiness.client.infrastructure.controller.dto.input.ClientInputDTO;
import com.bosonit.backbusiness.client.infrastructure.controller.dto.output.ClientOutputDTO;
import com.bosonit.backbusiness.client.infrastructure.repository.ClientRepository;
import com.bosonit.backbusiness.utils.exceptions.CustomUnprocessableException;
import com.bosonit.backbusiness.utils.kafka.producer.KafkaSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    KafkaSender kafkaSender;

    @Value("${server.port}")
    String port;

    @Value("${topic}")
    String topic;

    String CLIENT = "client";

    @Override
    public ClientOutputDTO addClient(ClientInputDTO clientInputDTO) {
        if (clientRepository.findByEmail(clientInputDTO.getEmail()) == null) {

            Client client = clientInputDtoToEntity(clientInputDTO);
            client.setIdClient(UUID.randomUUID());
            clientRepository.save(client);

            ClientOutputDTO clientDTO = new ClientOutputDTO(client);
            kafkaSender.sendMessage(topic, clientDTO, port, "create", CLIENT);

            return clientDTO;
        }

        throw new CustomUnprocessableException("There is already another client with email " + clientInputDTO.getEmail());
    }

    @Override
    public List<ClientOutputDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(ClientOutputDTO::new).toList();
    }

    @Override
    public ClientOutputDTO filterClientById(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow();
        return new ClientOutputDTO(client);
    }

    @Override
    public ClientOutputDTO filterClientByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        return new ClientOutputDTO(client);
    }

    @Override
    public ClientOutputDTO updateClient(UUID id, ClientInputDTO clientInputDTO) {

        if (clientRepository.findByEmail(clientInputDTO.getEmail()) == null) {

            Client client = clientRepository.findById(id).orElseThrow();
            client.setName(clientInputDTO.getName());
            client.setSurname(clientInputDTO.getSurname());
            client.setEmail(clientInputDTO.getEmail());
            client.setPassword(clientInputDTO.getPassword());
            clientRepository.save(client);

            ClientOutputDTO clientOutputDTO = EntityToClientOutDTO(client);
            kafkaSender.sendMessage(topic, clientOutputDTO, port, "update", CLIENT);

            return clientOutputDTO;
        }

        throw new CustomUnprocessableException("There is already another client with email " + clientInputDTO.getEmail());
    }

    @Override
    public void deleteClient(UUID id) {
        ClientOutputDTO clientOutputDTO = EntityToClientOutDTO(clientRepository.findById(id).orElseThrow());
        clientRepository.delete(clientRepository.findById(id).orElseThrow());
        kafkaSender.sendMessage(topic, clientOutputDTO, port, "delete", CLIENT);
    }

    public Client clientInputDtoToEntity(ClientInputDTO clientInputDTO) {
        Client client = new Client();
        client.setName(clientInputDTO.getName());
        client.setSurname(clientInputDTO.getSurname());
        client.setEmail(clientInputDTO.getEmail());
        client.setPassword(clientInputDTO.getPassword());

        return client;
    }

    public Client clientOutDtoToEntity(ClientOutputDTO clientOutputDTO) {
        Client client = new Client();
        client.setIdClient(clientOutputDTO.getIdClient());
        client.setName(clientOutputDTO.getName());
        client.setSurname(clientOutputDTO.getSurname());
        client.setEmail(clientOutputDTO.getEmail());
        client.setPassword(clientOutputDTO.getPassword());

        return client;
    }

    public ClientOutputDTO EntityToClientOutDTO(Client client) {
        ClientOutputDTO clientOutputDTO = new ClientOutputDTO();
        clientOutputDTO.setIdClient(client.getIdClient());
        clientOutputDTO.setName(client.getName());
        clientOutputDTO.setSurname(client.getSurname());
        clientOutputDTO.setEmail(client.getEmail());
        clientOutputDTO.setPassword(client.getPassword());

        return clientOutputDTO;
    }
}
