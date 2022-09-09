package com.bosonit.backweb.ticket.infrastructure.repository;

import com.bosonit.backweb.ticket.domain.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}
