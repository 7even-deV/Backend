package com.bosonit.backbusiness.trip.infrastructure.controller.crud;

import com.bosonit.backbusiness.trip.application.service.TripService;
import com.bosonit.backbusiness.trip.infrastructure.controller.dto.input.TripInputDTO;
import com.bosonit.backbusiness.trip.infrastructure.controller.dto.output.TripOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/business/trip")
public class UpdateTrip {

    @Autowired
    TripService tripService;

    @PutMapping("/{id}")
    public TripOutputDTO updateTrip(@PathVariable UUID id, @RequestBody TripInputDTO tripInputDTO) {
        return tripService.updateTrip(id, tripInputDTO);
    }
}
