package com.bosonit.backbusiness.client.infrastructure.kafka;

import com.bosonit.backbusiness.client.application.impl.ClientServiceImpl;
import com.bosonit.backbusiness.client.domain.Client;
import com.bosonit.backbusiness.client.infrastructure.controller.dto.output.ClientOutputDTO;
import com.bosonit.backbusiness.client.infrastructure.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
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
                clientRepository.save(client);

                log.info("CREATE SUCCESS");
            }

            case "update" -> {
                Client client = clientRepository.findById(clientOutputDTO.getIdClient()).orElseThrow();
                client.setName(clientOutputDTO.getName());
                client.setSurname(clientOutputDTO.getSurname());
                client.setEmail(clientOutputDTO.getEmail());
                client.setPassword(clientOutputDTO.getPassword());
                clientRepository.save(client);

                log.info("UPDATE SUCCESS");
            }

            case "delete" -> {
                clientRepository.delete(clientRepository.findById(clientOutputDTO.getIdClient()).orElseThrow());

                log.info("DELETE SUCCESS");
            }

            default -> {
                log.info("ERROR KAFKA SERVICE CLIENT! UNSPECIFIED ACTION (create, update or delete)");
            }
        }
    }
}
