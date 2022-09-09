package com.bosonit.backbusiness.ticket.infrastructure.controller.crud;

import com.bosonit.backbusiness.ticket.application.service.TicketService;
import com.bosonit.backbusiness.ticket.infrastructure.repository.TicketRepository;
import com.bosonit.backbusiness.utils.auth.AuthUtils;
import com.bosonit.backbusiness.utils.exceptions.CustomUnprocessableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/business/ticket")
public class DeleteTicket {

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable UUID id, @RequestHeader("Authorization") String auth) {

        UUID idToken = AuthUtils.getId(auth);
        if (!idToken.equals(ticketRepository.findById(id).orElseThrow().getClient().getIdClient())) {
            throw new CustomUnprocessableException("You can't delete someone else's ticket, only your own.");
        }

        ticketService.deleteTicket(id);
    }
}
