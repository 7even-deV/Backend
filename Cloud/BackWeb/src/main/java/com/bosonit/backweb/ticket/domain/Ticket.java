package com.bosonit.backweb.ticket.domain;

import com.bosonit.backweb.client.domain.Client;
import com.bosonit.backweb.trip.domain.Trip;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Ticket {

    @Id
    @Column(name = "id_ticket")
    private UUID idTicket;

    private String details;

    @ManyToOne
    @JoinColumn(name = "fk_client")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Client client;

    @ManyToOne
    @JoinColumn(name = "fk_trip")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Trip trip;
}
