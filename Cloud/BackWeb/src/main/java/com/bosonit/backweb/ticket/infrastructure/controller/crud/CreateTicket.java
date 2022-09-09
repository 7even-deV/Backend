package com.bosonit.backweb.ticket.infrastructure.controller.crud;

import com.bosonit.backweb.ticket.application.service.TicketService;
import com.bosonit.backweb.ticket.infrastructure.controller.dto.input.TicketInputDTO;
import com.bosonit.backweb.ticket.infrastructure.controller.dto.output.TicketOutputDTO;
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
public class CreateTicket {

    @Autowired
    TicketService ticketService;

    @Value("${urlbusiness}")
    String BUSINESS;

    @PostMapping
    public TicketOutputDTO addTicket(@RequestBody TicketInputDTO ticketInputDTO,
            @RequestHeader("Authorization") String auth) {
        HttpEntity<Object> request = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Void> re = new RestTemplate().exchange(BUSINESS + "/" + auth, HttpMethod.GET, request,
                Void.class);

        if (re.getStatusCode() == HttpStatus.OK) {
            UUID idToken = AuthUtils.getId(auth);
            if (!idToken.equals(ticketInputDTO.getIdClient())) {
                throw new CustomUnprocessableException(
                        "You cannot buy a ticket for a person with a different ID than the one you are authenticated with.");
            }
            return ticketService.addTicket(ticketInputDTO);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong Authentication!");
    }
}
