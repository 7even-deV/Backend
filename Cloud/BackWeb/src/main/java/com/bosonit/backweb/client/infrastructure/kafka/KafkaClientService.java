package com.bosonit.backweb.client.infrastructure.kafka;

import com.bosonit.backweb.client.application.impl.ClientServiceImpl;
import com.bosonit.backweb.client.domain.Client;
import com.bosonit.backweb.client.infrastructure.controller.dto.output.ClientOutputDTO;
import com.bosonit.backweb.client.infrastructure.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaClientService {

    @Autowired
    ClientServiceImpl clientService;

    @Autowired
    ClientRepository clientRepository;

    public void listenTopic(String action, ClientOutputDTO clientOutputDTO) {
        switch (action) {
            case "create" -> {
                Client client = clientService.clientOutDtoToEntity(clientOutputDTO);
                System.out.println(client);
                clientRepository.save(client);
                System.out.println("CREATE SUCCESS");
            }

            case "update" -> {
                Client client = clientRepository.findById(clientOutputDTO.getIdClient()).orElseThrow();
                client.setName(clientOutputDTO.getName());
                client.setSurname(clientOutputDTO.getSurname());
                client.setEmail(clientOutputDTO.getEmail());
                client.setPassword(clientOutputDTO.getPassword());

                clientRepository.save(client);
                System.out.println("UPDATE SUCCESS");
            }

            case "delete" -> {
                clientRepository.delete(clientRepository.findById(clientOutputDTO.getIdClient()).orElseThrow());
                System.out.println("DELETE SUCCESS");
            }
        }
    }
}
