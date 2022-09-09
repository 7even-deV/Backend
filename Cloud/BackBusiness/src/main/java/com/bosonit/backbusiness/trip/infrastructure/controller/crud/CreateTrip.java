package com.bosonit.backbusiness.trip.infrastructure.controller.crud;

import com.bosonit.backbusiness.trip.application.service.TripService;
import com.bosonit.backbusiness.trip.infrastructure.controller.dto.input.TripInputDTO;
import com.bosonit.backbusiness.trip.infrastructure.controller.dto.output.TripOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business/trip")
public class CreateTrip {

    @Autowired
    TripService tripService;

    @PostMapping
    public TripOutputDTO addTrip(@RequestBody TripInputDTO tripInputDTO) {
        return tripService.addTrip(tripInputDTO);
    }
}
