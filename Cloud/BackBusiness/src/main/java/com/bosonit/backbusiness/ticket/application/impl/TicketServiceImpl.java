package com.bosonit.backbusiness.ticket.application.impl;

import com.bosonit.backbusiness.client.domain.Client;
import com.bosonit.backbusiness.client.infrastructure.repository.ClientRepository;
import com.bosonit.backbusiness.ticket.application.impl.domain.Mail;
import com.bosonit.backbusiness.mail.infrastructure.repository.MailRepository;
import com.bosonit.backbusiness.ticket.application.service.TicketService;
import com.bosonit.backbusiness.ticket.domain.Ticket;
import com.bosonit.backbusiness.ticket.infrastructure.controller.dto.input.TicketInputDTO;
import com.bosonit.backbusiness.ticket.infrastructure.controller.dto.output.TicketOutputDTO;
import com.bosonit.backbusiness.ticket.infrastructure.repository.TicketRepository;
import com.bosonit.backbusiness.trip.domain.Trip;
import com.bosonit.backbusiness.trip.infrastructure.repository.TripRepository;
import com.bosonit.backbusiness.utils.exceptions.CustomUnprocessableException;
import com.bosonit.backbusiness.utils.kafka.producer.KafkaSender;
import com.bosonit.backbusiness.utils.mail.MailSenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    MailRepository mailRepository;

    @Autowired
    KafkaSender kafkaSender;

    @Autowired
    MailSenderService mailSenderService;

    @Value("${server.port}")
    String port;

    @Value("${topic}")
    String topic;

    String TICKET = "ticket";

    String DENIED = "denied";

    String CREATE = "create";

    @Override
    public TicketOutputDTO addTicket(TicketInputDTO ticketInputDTO) {
        Client client = clientRepository.findById(ticketInputDTO.getIdClient()).orElseThrow();
        Trip trip = tripRepository.findById(ticketInputDTO.getIdTrip()).stream().findFirst().orElseThrow();

        if (trip.isIssue()) {
            Ticket ticket = ticketInputDtoToEntity(ticketInputDTO);
            trip.setIncreaseDeniedSeats(1);
            tripRepository.save(trip);

            TicketOutputDTO ticketOutputDTO = new TicketOutputDTO(ticket);
            kafkaSender.sendMessage(topic, ticketOutputDTO, port, DENIED, TICKET);

            throw new CustomUnprocessableException("Trip canceled. (Maintenance, strike, etc.)");
        }

        for (int i = 0; i < client.getTickets().size(); i++) {
            if (client.getTickets().get(i).getTrip().getIdTrip().equals(ticketInputDTO.getIdTrip())) {
                trip.setIncreaseDeniedSeats(1);
                tripRepository.save(trip);

                Ticket ticket = ticketInputDtoToEntity(ticketInputDTO);
                TicketOutputDTO ticketOutputDTO = new TicketOutputDTO(ticket);
                kafkaSender.sendMessage(topic, ticketOutputDTO, port, DENIED, TICKET);
                throw new CustomUnprocessableException("The client already has a ticket for the route: "
                        + ticketInputDTO.getIdTrip() + " only 1 per person.");
            }
        }

        Ticket ticket = ticketInputDtoToEntity(ticketInputDTO);
        ticket.setIdTicket(UUID.randomUUID());

        if (trip.getSeats() >= 1) {
            trip.setDecreaseSeats(1);
            ticketRepository.save(ticket);

            TicketOutputDTO ticketOutputDTO = new TicketOutputDTO(ticket);
            kafkaSender.sendMessage(topic, ticketOutputDTO, port, CREATE, TICKET);

            mailSenderService.sendMail(
                    ticket.getClient().getEmail(),
                    "Purchased Ticket!",
                    "We inform you that you have bought a ticket for the trip from "
                            + trip.getDeparture() + " to " + trip.getArrival() + ".\n"
                            + "Your trip identifier is: " + ticket.getIdTicket());

            Mail mail = new Mail(UUID.randomUUID(), trip.getDate(), trip.getDeparture(), trip.getArrival(),
                    ticket.getClient().getEmail(), "Purchased Ticket!");
            mailRepository.save(mail);
            kafkaSender.sendMessage(topic, mail, port, CREATE, "mail");
            return ticketOutputDTO;
        }

        trip.setIncreaseDeniedSeats(1);
        tripRepository.save(trip);
        TicketOutputDTO ticketOutputDTO = new TicketOutputDTO(ticket);
        kafkaSender.sendMessage(topic, ticketOutputDTO, port, DENIED, TICKET);

        throw new CustomUnprocessableException("There are no places left for this trip.");
    }

    @Override
    public List<TicketOutputDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(TicketOutputDTO::new).toList();
    }

    @Override
    public TicketOutputDTO filterTicketById(UUID id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        return new TicketOutputDTO(ticket);
    }

    @Override
    public void deleteTicket(UUID id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        Trip trip = tripRepository.findById(ticketRepository.findById(id).orElseThrow().getTrip().getIdTrip())
                .orElseThrow();
        ticketRepository.delete(ticketRepository.findById(id).orElseThrow());
        trip.setIncreaseSeats(1);
        tripRepository.save(trip);

        TicketOutputDTO ticketOutputDTO = entityToTicketOutDTO(ticket);
        kafkaSender.sendMessage(topic, ticketOutputDTO, port, "delete", TICKET);

        mailSenderService.sendMail(
                ticket.getClient().getEmail(),
                "Canceled Ticket!",
                "We inform you that the travel ticket from "
                        + trip.getDeparture() + " to " + trip.getArrival()
                        + " has been cancelled.");

        Mail mail = new Mail(UUID.randomUUID(), trip.getDate(), trip.getDeparture(), trip.getArrival(),
                ticket.getClient().getEmail(), "Canceled Ticket!");
        mailRepository.save(mail);
        kafkaSender.sendMessage(topic, mail, port, CREATE, "mail");
    }

    public Ticket ticketInputDtoToEntity(TicketInputDTO ticketInputDTO) {
        Ticket ticket = new Ticket();
        ticket.setDetails(ticketInputDTO.getDetails());
        ticket.setClient(clientRepository.findById(ticketInputDTO.getIdClient()).orElseThrow());
        ticket.setTrip(tripRepository.findById(ticketInputDTO.getIdTrip()).orElseThrow());

        return ticket;
    }

    public Ticket ticketOutDtoToEntity(TicketOutputDTO ticketOutputDTO) {
        Ticket ticket = new Ticket();
        ticket.setIdTicket(ticketOutputDTO.getIdTicket());
        ticket.setDetails(ticketOutputDTO.getDetails());
        ticket.setClient(clientRepository.findById(ticketOutputDTO.getIdClient()).orElseThrow());
        ticket.setTrip(tripRepository.findById(ticketOutputDTO.getIdTrip()).orElseThrow());

        return ticket;
    }

    public TicketOutputDTO entityToTicketOutDTO(Ticket ticket) {
        TicketOutputDTO ticketOutputDTO = new TicketOutputDTO();
        ticketOutputDTO.setIdTicket(ticket.getIdTicket());
        ticketOutputDTO.setDetails(ticket.getDetails());
        ticketOutputDTO.setIdClient(ticket.getClient().getIdClient());
        ticketOutputDTO.setIdTrip(ticket.getTrip().getIdTrip());

        return ticketOutputDTO;
    }
}
