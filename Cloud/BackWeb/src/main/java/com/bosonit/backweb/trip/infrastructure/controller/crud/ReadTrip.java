package com.bosonit.backweb.trip.infrastructure.controller.crud;

import com.bosonit.backweb.trip.application.service.TripService;
import com.bosonit.backweb.trip.infrastructure.controller.dto.output.TripCensoredOutputDTO;
import com.bosonit.backweb.trip.infrastructure.controller.dto.output.TripOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/business/trip")
public class ReadTrip {

    @Autowired
    TripService tripService;

    // @GetMapping
    // public List<TripOutputDTO> findAll() {
    //     return tripService.getAllTrip();
    // }

    @GetMapping("/{id}")
    public TripOutputDTO filterTripById(@PathVariable UUID id) {
        return tripService.filterTripById(id);
    }

    @GetMapping("/details")
    public List<TripOutputDTO> findByDepartureAndArrivalAndDate(@RequestParam String departure,
            @RequestParam String arrival,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
        return tripService.findByDepartureAndArrivalAndDate(departure, arrival, date);
    }

    @GetMapping("/detailsLocalDate")
    public List<TripCensoredOutputDTO> findByDepartureAndArrivalAndLocalDate(@RequestParam String departure,
            @RequestParam String arrival, @RequestParam String localDate) {
        return tripService.findByDepartureAndArrivalAndLocalDate(departure, arrival, localDate);
    }
}
