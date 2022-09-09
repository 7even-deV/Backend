package com.bosonit.backbusiness.trip.application.impl;

import com.bosonit.backbusiness.ticket.application.impl.domain.Mail;
import com.bosonit.backbusiness.mail.infrastructure.repository.MailRepository;
import com.bosonit.backbusiness.trip.application.service.TripService;
import com.bosonit.backbusiness.trip.domain.Trip;
import com.bosonit.backbusiness.trip.infrastructure.controller.dto.input.TripInputDTO;
import com.bosonit.backbusiness.trip.infrastructure.controller.dto.output.TripCensoredOutputDTO;
import com.bosonit.backbusiness.trip.infrastructure.controller.dto.output.TripOutputDTO;
import com.bosonit.backbusiness.trip.infrastructure.repository.TripRepository;
import com.bosonit.backbusiness.utils.kafka.producer.KafkaSender;
import com.bosonit.backbusiness.utils.mail.MailSenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    MailRepository mailRepository;

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    KafkaSender kafkaSender;

    @Value("${server.port}")
    String port;

    @Value("${topic}")
    String topic;

    String TRIP = "trip";

    @Override
    public TripOutputDTO addTrip(TripInputDTO tripInputDTO) {
        Trip trip = tripInputDtoToEntity(tripInputDTO);
        if (tripInputDTO.isIssue()) {
            trip.setSeats(0);
        }

        trip.setIdTrip(UUID.randomUUID());
        tripRepository.save(trip);

        TripOutputDTO tripOutputDTO = new TripOutputDTO(trip);
        kafkaSender.sendMessage(topic, tripOutputDTO, port, "create", TRIP);

        return tripOutputDTO;
    }

    @Override
    public List<TripOutputDTO> getAllTrip() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream().map(TripOutputDTO::new).toList();
    }

    @Override
    public TripOutputDTO filterTripById(UUID id) {
        Trip trip = tripRepository.findById(id).orElseThrow();
        return new TripOutputDTO(trip);
    }

    @Override
    public List<TripOutputDTO> findByDepartureAndArrivalAndDate(String departure, String arrival, Date date) {
        List<Trip> trips = tripRepository.findByDepartureAndArrivalAndDate(departure, arrival, date);
        return trips.stream().map(TripOutputDTO::new).toList();
    }

    @Override
    public List<TripCensoredOutputDTO> findByDepartureAndArrivalAndLocalDate(String departure, String arrival,
                                                                             String date) {
        List<Trip> trips = tripRepository.findByDepartureAndArrivalAndLocalDate(departure, arrival, date);
        return trips.stream().map(TripCensoredOutputDTO::new).toList();
    }

    @Override
    public TripOutputDTO updateTrip(UUID id, TripInputDTO tripInputDTO) {
        Trip trip = tripRepository.findById(id).orElseThrow();

        if (tripInputDTO.isIssue()) {
            trip.setSeats(0);
            for (int i = 0; i < trip.getTickets().size(); i++) {
                mailSenderService.sendMail(
                        trip.getTickets().get(i).getClient().getEmail(),
                        "Important announcement. Your trip has been canceled",
                        "We inform you that your trip from " + trip.getDeparture() + " to " + trip.getArrival()
                                + " for the date " + trip.getDate()
                                + " has been forcibly canceled due to a strike, breakdown or other exceptional reason.\n"
                                + "The identifier of the canceled ticket is: "
                                + trip.getTickets().get(i).getIdTicket());

                Mail mail = new Mail(UUID.randomUUID(), trip.getDate(), trip.getDeparture(), trip.getArrival(),
                        trip.getTickets().get(i).getClient().getEmail(), "FORCED CANCELLATION!");
                mailRepository.save(mail);
                kafkaSender.sendMessage(topic, mail, port, "create", "mail");
            }
        }

        if (!tripInputDTO.isIssue() && trip.isIssue()) {
            trip.setSeats(40);
        }

        trip.setDate(tripInputDTO.getDate());
        trip.setDeparture(tripInputDTO.getDeparture());
        trip.setArrival(tripInputDTO.getArrival());
        trip.setIssue(tripInputDTO.isIssue());
        tripRepository.save(trip);

        TripOutputDTO tripOutputDTO = EntityToTripOutDTO(trip);
        kafkaSender.sendMessage(topic, tripOutputDTO, port, "update", TRIP);

        return new TripOutputDTO(trip);
    }

    @Override
    public void deleteTrip(UUID id) {
        TripOutputDTO tripOutputDTO = EntityToTripOutDTO(tripRepository.findById(id).orElseThrow());
        tripRepository.delete(tripRepository.findById(id).orElseThrow());
        kafkaSender.sendMessage(topic, tripOutputDTO, port, "delete", TRIP);
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
