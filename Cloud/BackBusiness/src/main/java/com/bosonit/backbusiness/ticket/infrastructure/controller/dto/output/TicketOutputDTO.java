package com.bosonit.backbusiness.ticket.infrastructure.controller.dto.output;

import com.bosonit.backbusiness.ticket.domain.Ticket;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TicketOutputDTO implements Serializable {

    private UUID idTicket;
    private String details;
    private UUID idClient;
    private UUID idTrip;

    public TicketOutputDTO(Ticket ticket) {
        setIdTicket(ticket.getIdTicket());
        setDetails(ticket.getDetails());
        setIdClient(ticket.getClient().getIdClient());
        setIdTrip(ticket.getTrip().getIdTrip());
    }
}
