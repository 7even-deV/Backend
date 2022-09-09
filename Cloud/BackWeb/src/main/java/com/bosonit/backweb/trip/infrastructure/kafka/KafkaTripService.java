package com.bosonit.backweb.trip.infrastructure.kafka;

import com.bosonit.backweb.trip.application.impl.TripServiceImpl;
import com.bosonit.backweb.trip.domain.Trip;
import com.bosonit.backweb.trip.infrastructure.controller.dto.output.TripOutputDTO;
import com.bosonit.backweb.trip.infrastructure.repository.TripRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaTripService {

    @Autowired
    TripServiceImpl tripService;

    @Autowired
    TripRepository tripRepository;

    public void listenTopic(String action, TripOutputDTO tripOutputDTO) {
        switch (action) {
            case "create" -> {
                Trip trip = tripService.tripOutDtoToEntity(tripOutputDTO);
                System.out.println(trip);
                tripRepository.save(trip);
                System.out.println("CREATE SUCCESS");
            }

            case "update" -> {
                Trip trip = tripRepository.findById(tripOutputDTO.getIdTrip()).orElseThrow();
                trip.setDate(tripOutputDTO.getDate());
                trip.setDeparture(tripOutputDTO.getDeparture());
                trip.setArrival(tripOutputDTO.getArrival());
                trip.setIssue(tripOutputDTO.isIssue());
                trip.setSeats(tripOutputDTO.getSeats());

                tripRepository.save(trip);
                System.out.println("UPDATE SUCCESS");
            }

            case "delete" -> {
                tripRepository.delete(tripRepository.findById(tripOutputDTO.getIdTrip()).orElseThrow());
                System.out.println("DELETE SUCCESS");
            }
        }
    }
}
