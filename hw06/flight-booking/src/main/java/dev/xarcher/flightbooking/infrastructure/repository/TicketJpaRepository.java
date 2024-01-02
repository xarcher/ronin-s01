package dev.xarcher.flightbooking.infrastructure.repository;

import dev.xarcher.flightbooking.domain.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketJpaRepository extends JpaRepository<Long, Ticket> {
}
