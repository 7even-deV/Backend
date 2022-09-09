package com.bosonit.backweb.trip.infrastructure.controller.dto.output;

import com.bosonit.backweb.trip.domain.Trip;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TripOutputDTO implements Serializable {

    private UUID idTrip;
    private Date date;
    private String departure;
    private String arrival;
    private Integer seats;
    private Integer deniedSeats;
    private boolean issue;
    private List<UUID> tickets;

    public TripOutputDTO(Trip trip) {
        setIdTrip(trip.getIdTrip());
        setDate(trip.getDate());
        setDeparture(trip.getDeparture());
        setArrival(trip.getArrival());
        setSeats(trip.getSeats());
        setDeniedSeats(trip.getDeniedSeats());
        setIssue(trip.isIssue());

        List<UUID> tickets = new ArrayList<>();
        if (trip.getTickets() != null && trip.getTickets().size() != 0) {
            for (int i = 0; i < trip.getTickets().size(); i++) {
                tickets.add(trip.getTickets().get(i).getIdTicket());
            }
        }
        setTickets(tickets);
    }
}
