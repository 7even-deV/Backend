package com.bosonit.backbusiness.ticket.infrastructure.kafka;

import com.bosonit.backbusiness.ticket.application.impl.TicketServiceImpl;
import com.bosonit.backbusiness.ticket.domain.Ticket;
import com.bosonit.backbusiness.ticket.infrastructure.controller.dto.output.TicketOutputDTO;
import com.bosonit.backbusiness.ticket.infrastructure.repository.TicketRepository;
import com.bosonit.backbusiness.trip.domain.Trip;
import com.bosonit.backbusiness.trip.infrastructure.repository.TripRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaTicketService {

    @Autowired
    TicketServiceImpl ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TripRepository tripRepository;

    public void listenTopic(String action, TicketOutputDTO ticketOutputDTO) {
        switch (action) {
            case "create" -> {
                Ticket ticket = ticketService.ticketOutDtoToEntity(ticketOutputDTO);
                Trip trip = tripRepository.findById(ticketOutputDTO.getIdTrip()).stream().findFirst().orElseThrow();
                trip.setDecreaseSeats(1);
                ticketRepository.save(ticket);
                tripRepository.save(trip);

                log.info("CREATE SUCCESS");
            }

            case "update" -> {
                log.info("TICKETS CAN´T BE UPDATED, ASK FOR REFUND AND BUY ANOTHER");
            }

            case "delete" -> {
                ticketRepository.delete(ticketRepository.findById(ticketOutputDTO.getIdTicket()).orElseThrow());
                Trip trip = tripRepository.findById(ticketOutputDTO.getIdTrip()).stream().findFirst().orElseThrow();
                trip.setIncreaseSeats(1);
                tripRepository.save(trip);

                log.info("DELETE SUCCESS");
            }

            case "denied" -> {
                Trip trip = tripRepository.findById(ticketOutputDTO.getIdTrip()).stream().findFirst().orElseThrow();
                trip.setIncreaseDeniedSeats(1);
                tripRepository.save(trip);

                log.info("DENIED COUNTER INCREASED SUCCESSFULLY");
            }

            default -> {
                log.info("ERROR KAFKA SERVICE TICKET! UNSPECIFIED ACTION (create, update, deny or delete)");
            }
        }
    }
}
