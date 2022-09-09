package com.bosonit.backbusiness.trip.application.service;

import com.bosonit.backbusiness.trip.infrastructure.controller.dto.input.TripInputDTO;
import com.bosonit.backbusiness.trip.infrastructure.controller.dto.output.TripCensoredOutputDTO;
import com.bosonit.backbusiness.trip.infrastructure.controller.dto.output.TripOutputDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TripService {

    TripOutputDTO addTrip(TripInputDTO tripInputDTO);
    List<TripOutputDTO> getAllTrip();
    TripOutputDTO filterTripById(UUID id);
    List<TripOutputDTO> findByDepartureAndArrivalAndDate(String departure, String arrival, Date date);
    List<TripCensoredOutputDTO> findByDepartureAndArrivalAndLocalDate(String departure, String arrival, String date);
    TripOutputDTO updateTrip(UUID id, TripInputDTO tripInputDTO);
    void deleteTrip(UUID id);
}
