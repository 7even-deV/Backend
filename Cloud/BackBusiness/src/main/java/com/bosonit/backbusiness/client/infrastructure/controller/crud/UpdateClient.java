package com.bosonit.backbusiness.client.infrastructure.controller.crud;

import com.bosonit.backbusiness.client.application.service.ClientService;
import com.bosonit.backbusiness.client.infrastructure.controller.dto.input.ClientInputDTO;
import com.bosonit.backbusiness.client.infrastructure.controller.dto.output.ClientOutputDTO;
import com.bosonit.backbusiness.utils.auth.AuthUtils;
import com.bosonit.backbusiness.utils.exceptions.CustomUnprocessableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/business/client")
public class UpdateClient {

    @Autowired
    ClientService clientService;

    @PutMapping("/{id}")
    public ClientOutputDTO updateClient(@PathVariable UUID id, @RequestBody ClientInputDTO clientInputDTO,
                                        @RequestHeader("Authorization") String auth) {
        UUID idToken = AuthUtils.getId(auth);
        if (!idToken.equals(id)) {
            throw new CustomUnprocessableException(
                    "The authenticated client ID does not correspond to the client you want to update.");
        }

        return clientService.updateClient(id, clientInputDTO);
    }
}
