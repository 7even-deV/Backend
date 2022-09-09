package com.bosonit.backweb.ticket.application.service;

import com.bosonit.backweb.ticket.infrastructure.controller.dto.input.TicketInputDTO;
import com.bosonit.backweb.ticket.infrastructure.controller.dto.output.TicketOutputDTO;

import java.util.List;
import java.util.UUID;

public interface TicketService {

    TicketOutputDTO addTicket(TicketInputDTO ticketInputDTO);
    List<TicketOutputDTO> getAllTickets();
    TicketOutputDTO filterTicketById(UUID id);
    void deleteTicket(UUID id);
}
