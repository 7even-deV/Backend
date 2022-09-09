package com.bosonit.backbusiness.ticket.infrastructure.repository;

import com.bosonit.backbusiness.ticket.domain.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}
