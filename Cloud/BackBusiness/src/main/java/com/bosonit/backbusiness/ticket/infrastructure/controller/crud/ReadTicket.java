package com.bosonit.backbusiness.ticket.infrastructure.controller.crud;

import com.bosonit.backbusiness.ticket.application.service.TicketService;
import com.bosonit.backbusiness.ticket.infrastructure.controller.dto.output.TicketOutputDTO;
import com.bosonit.backbusiness.ticket.infrastructure.repository.TicketRepository;
import com.bosonit.backbusiness.utils.auth.AuthUtils;
import com.bosonit.backbusiness.utils.exceptions.CustomUnprocessableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/business/ticket")
public class ReadTicket {

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping
    public List<TicketOutputDTO> findAll() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public TicketOutputDTO filterTicketById(@PathVariable UUID id, @RequestHeader("Authorization") String auth) {
        UUID idToken = AuthUtils.getId(auth);
        if (!idToken.equals(ticketRepository.findById(id).orElseThrow().getClient().getIdClient())) {
            throw new CustomUnprocessableException(
                    "The authenticated person does not correspond to the person whose ticket you want to read.");
        }

        return ticketService.filterTicketById(id);
    }
}
