package com.bosonit.backbusiness.trip.infrastructure.kafka;

import com.bosonit.backbusiness.trip.application.impl.TripServiceImpl;
import com.bosonit.backbusiness.trip.domain.Trip;
import com.bosonit.backbusiness.trip.infrastructure.controller.dto.output.TripOutputDTO;
import com.bosonit.backbusiness.trip.infrastructure.repository.TripRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
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
                tripRepository.save(trip);

                log.info("CREATE SUCCESS");
            }

            case "update" -> {
                Trip trip = tripRepository.findById(tripOutputDTO.getIdTrip()).orElseThrow();
                trip.setDate(tripOutputDTO.getDate());
                trip.setDeparture(tripOutputDTO.getDeparture());
                trip.setArrival(tripOutputDTO.getArrival());
                trip.setIssue(tripOutputDTO.isIssue());
                trip.setSeats(tripOutputDTO.getSeats());
                tripRepository.save(trip);

                log.info("UPDATE SUCCESS");
            }

            case "delete" -> {
                tripRepository.delete(tripRepository.findById(tripOutputDTO.getIdTrip()).orElseThrow());

                log.info("DELETE SUCCESS");
            }

            default -> {
                log.info("ERROR KAFKA SERVICE TRIP! UNSPECIFIED ACTION (create, update or delete)");
            }
        }
    }
}
