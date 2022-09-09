package com.bosonit.backbusiness;

import com.bosonit.backbusiness.client.application.impl.ClientServiceImpl;
import com.bosonit.backbusiness.client.infrastructure.controller.dto.input.ClientInputDTO;
import com.bosonit.backbusiness.client.infrastructure.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackBusinessApplication {

    @Autowired
    ClientRepository clientRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackBusinessApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ClientServiceImpl clientService) {
        return args -> {
            if (clientRepository.findByEmail("adminbus@business.local") == null) {
                clientService.addClient(
                        new ClientInputDTO(
                                "Admin",
                                "Bus",
                                "adminbus@business.local",
                                "1234"));
            }
        };
    }
}
