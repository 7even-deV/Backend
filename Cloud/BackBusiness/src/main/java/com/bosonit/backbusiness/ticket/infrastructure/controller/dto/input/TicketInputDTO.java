package com.bosonit.backbusiness.ticket.infrastructure.controller.dto.input;

import lombok.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TicketInputDTO implements Serializable {

    private String details;

    @NonNull
    private UUID idClient;

    @NonNull
    private UUID idTrip;
}
