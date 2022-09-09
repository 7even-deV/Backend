package com.bosonit.backweb.ticket.infrastructure.kafka;

import com.bosonit.backweb.client.infrastructure.repository.ClientRepository;
import com.bosonit.backweb.ticket.application.impl.TicketServiceImpl;
import com.bosonit.backweb.ticket.domain.Ticket;
import com.bosonit.backweb.ticket.infrastructure.controller.dto.output.TicketOutputDTO;
import com.bosonit.backweb.ticket.infrastructure.repository.TicketRepository;
import com.bosonit.backweb.trip.domain.Trip;
import com.bosonit.backweb.trip.infrastructure.repository.TripRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaTicketService {

    @Autowired
    TicketServiceImpl ticketServiceImpl;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    TicketRepository ticketRepository;

    public void listenTopic(String action, TicketOutputDTO ticketOutputDTO) {
        switch (action) {
            case "create" -> {
                Ticket ticket = ticketServiceImpl.ticketOutDtoToEntity(ticketOutputDTO);
                System.out.println(ticket);
                Trip trip = tripRepository.findById(ticketOutputDTO.getIdTrip()).stream().findFirst().orElseThrow();
                trip.setDecreaseSeats(1);
                ticketRepository.save(ticket);
                tripRepository.save(trip);

                System.out.println("CREATE SUCCESS");
            }

            case "update" -> {
                // Ticket ticket = ticketRepository.findById(ticketOutputDTO.getIdTicket()).orElseThrow();
                // ticket.setDetails(ticketOutputDTO.getDetails());
                // ticket.setClient(clientRepository.findById(ticketOutputDTO.getIdClient()).orElseThrow());
                // ticket.setTrip(tripRepository.findById(ticketOutputDTO.getIdTrip()).orElseThrow());
                // ticketRepository.save(ticket);

                System.out.println("TICKETS CAN´T BE UPDATED, ASK FOR REFUND AND BUY ANOTHER");
            }

            case "delete" -> {
                ticketRepository.delete(ticketRepository.findById(ticketOutputDTO.getIdTicket()).orElseThrow());
                Trip trip = tripRepository.findById(ticketOutputDTO.getIdTrip()).stream().findFirst().orElseThrow();
                trip.setIncreaseSeats(1);
                tripRepository.save(trip);

                System.out.println("DELETE SUCCESS");
            }

            case "denied" -> {
                Trip trip = tripRepository.findById(ticketOutputDTO.getIdTrip()).stream().findFirst().orElseThrow();
                trip.setIncreaseDeniedSeats(1);
                tripRepository.save(trip);

                System.out.println("DENIED COUNTER INCREASED SUCCESSFULLY");
            }
        }
    }
}
