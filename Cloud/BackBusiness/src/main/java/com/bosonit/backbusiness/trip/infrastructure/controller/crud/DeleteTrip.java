package com.bosonit.backbusiness.trip.infrastructure.controller.crud;

import com.bosonit.backbusiness.trip.application.service.TripService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/business/trip")
public class DeleteTrip {

    @Autowired
    TripService tripService;

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable UUID id) {
        tripService.deleteTrip(id);
    }
}
