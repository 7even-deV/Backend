package com.bosonit.backweb.ticket.infrastructure.controller.dto.input;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TicketInputDTO implements Serializable {

    private String details;

    @NotNull
    private UUID idClient;

    @NotNull
    private UUID idTrip;
}
