package com.bosonit.backweb.ticket.infrastructure.controller.crud;

import com.bosonit.backweb.ticket.application.service.TicketService;
import com.bosonit.backweb.ticket.infrastructure.repository.TicketRepository;
import com.bosonit.backweb.utils.auth.AuthUtils;
import com.bosonit.backweb.utils.exceptions.CustomUnprocessableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/business/ticket")
public class DeleteTicket {

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @Value("${urlbusiness}")
    String BUSINESS;

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable UUID id, @RequestHeader("Authorization") String auth) {
        HttpEntity<Object> request = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Void> re = new RestTemplate().exchange(BUSINESS + "/" + auth, HttpMethod.GET, request,
                Void.class);

        if (re.getStatusCode() == HttpStatus.OK) {
            UUID idToken = AuthUtils.getId(auth);
            if (!idToken.equals(ticketRepository.findById(id).orElseThrow().getClient().getIdClient())) {
                throw new CustomUnprocessableException("You can't delete someone else's ticket, only your own.");
            }
            ticketService.deleteTicket(id);
            return;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong Authentication!");
    }
}
