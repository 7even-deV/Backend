package com.bosonit.backweb.trip.infrastructure.controller.dto.output;

import com.bosonit.backweb.trip.domain.Trip;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

// Denied tickets and the array of hold ticket IDs are not returned.
@Data
@NoArgsConstructor
public class TripCensoredOutputDTO implements Serializable {

    private UUID idTrip;
    private Date date;
    private String departure;
    private String arrival;
    private Integer seats;
    private boolean issue;

    public TripCensoredOutputDTO(Trip trip) {
        setIdTrip(trip.getIdTrip());
        setDate(trip.getDate());
        setDeparture(trip.getDeparture());
        setArrival(trip.getArrival());
        setSeats(trip.getSeats());
        setIssue(trip.isIssue());
    }
}
