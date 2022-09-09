package com.bosonit.backbusiness.ticket.infrastructure.controller.crud;

import com.bosonit.backbusiness.ticket.application.service.TicketService;
import com.bosonit.backbusiness.ticket.infrastructure.controller.dto.input.TicketInputDTO;
import com.bosonit.backbusiness.ticket.infrastructure.controller.dto.output.TicketOutputDTO;
import com.bosonit.backbusiness.utils.auth.AuthUtils;
import com.bosonit.backbusiness.utils.exceptions.CustomUnprocessableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/business/ticket")
public class CreateTicket {

    @Autowired
    TicketService ticketService;

    @PostMapping
    public TicketOutputDTO addTicket(@RequestBody TicketInputDTO ticketInputDTO,
                                     @RequestHeader("Authorization") String auth) {
        UUID idToken = AuthUtils.getId(auth);
        if (!idToken.equals(ticketInputDTO.getIdClient())) {
            throw new CustomUnprocessableException(
                    "You cannot buy a ticket for a person with a different ID than the one you are authenticated with.");
        }

        return ticketService.addTicket(ticketInputDTO);
    }
}
