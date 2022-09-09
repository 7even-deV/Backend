package com.bosonit.backweb.trip.application.impl;

import com.bosonit.backweb.trip.application.service.TripService;
import com.bosonit.backweb.trip.domain.Trip;
import com.bosonit.backweb.trip.infrastructure.controller.dto.input.TripInputDTO;
import com.bosonit.backweb.trip.infrastructure.controller.dto.output.TripCensoredOutputDTO;
import com.bosonit.backweb.trip.infrastructure.controller.dto.output.TripOutputDTO;
import com.bosonit.backweb.trip.infrastructure.repository.TripRepository;
import com.bosonit.backweb.utils.kafka.producer.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    KafkaSender kafkaSender;

    @Value("${server.port}")
    String port;

    @Value("${topic}")
    String topic;

    @Override
    public TripOutputDTO addTrip(TripInputDTO tripInputDTO) {
        Trip trip = tripInputDtoToEntity(tripInputDTO);
        if (tripInputDTO.isIssue()) {
            trip.setSeats(0);
        }

        trip.setIdTrip(UUID.randomUUID());
        tripRepository.save(trip);

        TripOutputDTO tripOutputDTO = new TripOutputDTO(trip);
        kafkaSender.sendMessage(topic, tripOutputDTO, port, "create", "trip");

        return tripOutputDTO;
    }

    @Override
    public List<TripOutputDTO> getAllTrip() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream().map(TripOutputDTO::new).collect(Collectors.toList());
    }

    @Override
    public TripOutputDTO filterTripById(UUID id) {
        Trip trip = tripRepository.findById(id).orElseThrow();
        return new TripOutputDTO(trip);
    }

    // If someone wants to buy a ticket, without knowing the tripId, we must look
    // for these fields.
    @Override
    public List<TripOutputDTO> findByDepartureAndArrivalAndDate(String departure, String arrival, Date date) {
        List<Trip> trips = tripRepository.findByDepartureAndArrivalAndDate(departure, arrival, date);
        return trips.stream().map(TripOutputDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<TripCensoredOutputDTO> findByDepartureAndArrivalAndLocalDate(String departure, String arrival,
            String date) {
        List<Trip> trips = tripRepository.findByDepartureAndArrivalAndLocalDate(departure, arrival, date);
        return trips.stream().map(TripCensoredOutputDTO::new).collect(Collectors.toList());
    }

    @Override
    public TripOutputDTO updateTrip(UUID id, TripInputDTO tripInputDTO) {
        Trip trip = tripRepository.findById(id).orElseThrow();

        if (tripInputDTO.isIssue()) {
            trip.setSeats(0);
        }

        if (!tripInputDTO.isIssue()) {
            if (trip.isIssue()) {
                trip.setSeats(40);
            }
        }

        trip.setDate(tripInputDTO.getDate());
        trip.setDeparture(tripInputDTO.getDeparture());
        trip.setArrival(tripInputDTO.getArrival());
        trip.setIssue(tripInputDTO.isIssue());
        tripRepository.save(trip);

        TripOutputDTO tripOutputDTO = EntityToTripOutDTO(trip);
        kafkaSender.sendMessage(topic, tripOutputDTO, port, "update", "trip");

        return new TripOutputDTO(trip);
    }

    @Override
    public void deleteTrip(UUID id) {
        TripOutputDTO tripOutputDTO = EntityToTripOutDTO(tripRepository.findById(id).orElseThrow());
        tripRepository.delete(tripRepository.findById(id).orElseThrow());
        kafkaSender.sendMessage(topic, tripOutputDTO, port, "delete", "trip");
    }

    public Trip tripInputDtoToEntity(TripInputDTO tripInputDTO) {
        Trip trip = new Trip();
        trip.setDate(tripInputDTO.getDate());
        trip.setDeparture(tripInputDTO.getDeparture());
        trip.setArrival(tripInputDTO.getArrival());
        trip.setIssue(tripInputDTO.isIssue());

        return trip;
    }

    public Trip tripOutDtoToEntity(TripOutputDTO tripOutputDTO) {
        Trip trip = new Trip();
        trip.setIdTrip(tripOutputDTO.getIdTrip());
        trip.setDate(tripOutputDTO.getDate());
        trip.setDeparture(tripOutputDTO.getDeparture());
        trip.setArrival(tripOutputDTO.getArrival());
        trip.setSeats(tripOutputDTO.getSeats());
        trip.setDeniedSeats(tripOutputDTO.getDeniedSeats());
        trip.setIssue(tripOutputDTO.isIssue());

        return trip;
    }

    public TripOutputDTO EntityToTripOutDTO(Trip trip) {
        TripOutputDTO tripOutputDTO = new TripOutputDTO();
        tripOutputDTO.setIdTrip(trip.getIdTrip());
        tripOutputDTO.setDate(trip.getDate());
        tripOutputDTO.setDeparture(trip.getDeparture());
        tripOutputDTO.setArrival(trip.getArrival());
        tripOutputDTO.setSeats(trip.getSeats());
        tripOutputDTO.setDeniedSeats(trip.getDeniedSeats());
        tripOutputDTO.setIssue(trip.isIssue());

        return tripOutputDTO;
    }
}
